package com.tkach360.evolutionsimulation;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/** класс бота
 * основной класс проекта*/
public class Bot extends UpdatableTileObject{

    /** количество энергии которое тратится за один тик */
    public static int energyPerTik = 1;
    /** максимально возможное количество энергии бота */
    public static int maxEnergy = 100;
    /** энергия, оставляемая ботом в почве после смерти */
    public static int residualEnergyInSoil = 1;
    /** минимальная энергия необходимая для размножения, она передается потомку */
    public static int minEnergyReproduction = 10;
    /** начальное количество энергии бота */
    public static int defaultEnergy = minEnergyReproduction;
    /** показатель того, на сколько параметры потомка могут отличаться от параметров родителя */
    public static int mutationSpread = 1;
    /** максимальный возраст бота (если -1 то без старости) */
    public static int maxOld = 100;

    /** устанавливается если последним источником энергии бота был фотосинтез */
    public static final Color PHOTOSYNTHESIS_COLOR = Color.rgb(0, 210, 0);
    /** устанавливается если последним источником энергии бота было хищничество */
    public static final Color PREDATION_COLOR = Color.rgb(210, 0, 0);
    /** устанавливается если последним источником энергии бота была энергия почвы */
    public static final Color SOIL_COLOR = Color.rgb(0, 0, 210);

    private final IBehavior behavior;
    private Direction direction;
    private Color color; // бот получает цвет в зависимости от последнего источника энергии
    private int energy;
    private int predation;
    private int photosynthesis;
    private int soil;
    private int old;

    private ArrayList<EnergySource> edible = new ArrayList<>(Arrays.asList(EnergySource.PHOTOSYNTHESIS, EnergySource.PHOTOSYNTHESIS, EnergySource.PHOTOSYNTHESIS, EnergySource.PHOTOSYNTHESIS)); // список последних пяти источников энергии 0 - фотосинтез, 1 - почва, 2 - хищничество

    public Bot(Tile tile, IBehavior behavior, Direction direction, Color color, int energy, int predation, int photosynthesis, int soil) {
        super(tile, TypeTileObject.Bot);
        this.behavior = behavior;
        this.direction = direction;
        this.color = color;
        this.energy = energy;
        this.predation = predation;
        this.photosynthesis = photosynthesis;
        this.soil = soil;
        tile.setAbstractTileObject(this);
    }

    public Bot(Tile tile, Random random){
        super(tile, TypeTileObject.Bot);
        this.tile = tile;
        this.behavior = new NeuralNetwork(); //TODO изменить при дорпботке механизма неследования поведения
        this.direction = Direction.getRandom(random);
        this.color = PHOTOSYNTHESIS_COLOR;
        this.energy = defaultEnergy;
        this.predation = 5;
        this.photosynthesis = 5;
        this.soil = 5;

        tile.setAbstractTileObject(this);
    }

    private void tryMove(int tileIndex){
        Tile tileForward = getTileInVisibleArea(tileIndex);
        if(tileForward.getAbstractTileObject() == null){
            changeTile(tileForward);
        }
    }

    public Tile getTileInVisibleArea(int index){

        int[][] arrayBias = Direction.getArrayVisibleAreaBias(this.direction);

        int tX = this.tile.getCx() + arrayBias[0][index];
        int tY = this.tile.getCy() + arrayBias[1][index];

        if(tX > TileMap.getInstance().getCountColumns() - 1) tX = 0;
        if(tY > TileMap.getInstance().getCountRows() - 1) tY = 0;
        if(tX < 0) tX = TileMap.getInstance().getCountColumns() - 1;
        if(tY < 0) tY = TileMap.getInstance().getCountRows() - 1;

        return TileMap.getInstance().getTiles()[tX][tY];
    }

    /** возвращает список трех тайлов перед ботом */
    public ArrayList<Tile> getTilesInVisibleArea(){
        ArrayList<Tile> tiles = new ArrayList<Tile>();

        for(int i = 0; i < 3; i++){
            Tile tile = getTileInVisibleArea(i);
            tiles.add(tile);
        }

        return tiles;
    }

    /** возвращает список трех тайлов перед ботом */
    public ArrayList<Pair<Integer, Tile>> getTilesInVisibleAreaWitchIndex(){
        ArrayList<Pair<Integer, Tile>> tiles = new ArrayList<Pair<Integer, Tile>>();

        for(int i = 0; i < 3; i++){
            Tile tile = getTileInVisibleArea(i);
            tiles.add(new Pair<>(i, tile));
        }

        return tiles;
    }

    /** возвращает список тайлов поблизости (8 тайлов вокруг)
     * среди этих тайлов нет передаваемого тайла */
    public ArrayList<Tile> getTilesNear(){
        ArrayList<Tile> tiles = new ArrayList<Tile>();

        for(int i = 0; i < 9; i++){
            if(i != 4) {
                Tile tile = getTileInVisibleArea(i);
                tiles.add(tile);
            }
        }

        return tiles;
    }

    // TODO: этот метод отвечает за принятие решения о действии и собственно действии
    public void update(){
        switch(behavior.decide(this)){
            case MOVE_0: tryMove(0); break;
            case MOVE_1: tryMove(1); break;
            case MOVE_2: tryMove(2); break;
            case ROTATE_LEFT: rotateLeft(); break;
            case ROTATE_RIGHT: rotateRight(); break;
            case ROTATE_DOWN: rotateDown(); break;
            case PHOTOSYNTHESIZE: photosynthesize(); break;
            case CONSUME_SOIL: consumeSoil(); break;
            case EAT_0: tryEat(0); break;
            case EAT_1: tryEat(1); break;
            case EAT_2: tryEat(2); break;
            case PRODUCE_0: tryProduceNewBot(0); break;
            case PRODUCE_1: tryProduceNewBot(1); break;
            case PRODUCE_2: tryProduceNewBot(2); break;
        }

        updateState();
    }

    private Color getColorFromLastEdible(){
        int r = 0;
        int g = 0;
        int b = 0;
        for(EnergySource i : edible){
            switch (i){
                case PHOTOSYNTHESIS: g += 63; break;
                case SOIL: b += 63; break;
                case PREDATION: r += 63; break;
            }
        }

        return Color.rgb(r, g, b);
    }

    public EnergySource getLastEdible(){
        return edible.getLast();
    }

    private void addSourceEnergy(EnergySource i){
        edible.removeFirst();
        edible.add(i);
    }

    private void updateColor(EnergySource i){
        addSourceEnergy(i);
        setColor(getColorFromLastEdible());
    }

    private void rotateLeft(){
        switch (this.direction){
            case UP: this.direction = Direction.LEFT; break;
            case LEFT: this.direction = Direction.DOWN; break;
            case DOWN: this.direction = Direction.RIGHT; break;
            case RIGHT: this.direction = Direction.UP; break;
        }
    }

    private void rotateRight(){
        switch (this.direction){
            case UP: this.direction = Direction.RIGHT; break;
            case LEFT: this.direction = Direction.UP; break;
            case DOWN: this.direction = Direction.LEFT; break;
            case RIGHT: this.direction = Direction.DOWN; break;
        }
    }

    private void rotateDown(){
        switch (this.direction){
            case UP: this.direction = Direction.DOWN; break;
            case LEFT: this.direction = Direction.RIGHT; break;
            case DOWN: this.direction = Direction.UP; break;
            case RIGHT: this.direction = Direction.LEFT; break;
        }
    }

    public void photosynthesize(){
        changeEnergy(getEnergyFromSource(this.tile.getLighting(), photosynthesis));
        updateColor(EnergySource.PHOTOSYNTHESIS);
    }

    public void consumeSoil(){
        changeEnergy(getEnergyFromSource(this.tile.getSoilEnergy(), soil));
        this.tile.setSoilEnergy(0);
        updateColor(EnergySource.SOIL);
    }

    public void eat(Bot bot){
        changeEnergy(getEnergyFromSource(bot.getEnergy() / 10, predation));
        Tile tile = bot.getTile();
        bot.die();
        changeTile(tile);
        updateColor(EnergySource.PREDATION);
    }

    public void tryEat(int index){
        Tile tile = getTileInVisibleArea(index);

        if(tile.getAbstractTileObject() != null){
            if(tile.getAbstractTileObject().getType() == TypeTileObject.Bot) {
                eat((Bot)tile.getAbstractTileObject());
            }
            else tryMove(index);
        }
        else tryMove(index);
    }

    private int getEnergyFromSource(int source, int efficiency){
        return source * efficiency;
    }

    private void changeEnergy(int delta){
        setEnergy(this.energy + delta);
    }

    private void updateState(){
        changeEnergy(-energyPerTik);
        if(this.energy == 0) die();

        if(maxOld != -1) {
            if (this.old == 100) die();
            this.old++;
        }
        //if(this.behavior instanceof NeuralNetwork) ((NeuralNetwork) this.behavior).getPerceptronNetwork().mutate(0.1, 0.1);
    }

    // TODO: нужно доделать механику размножения с учетом алгоритма поведения
    public void produceNewBot(Tile tile){
        Random random = new Random();

        Bot newBot = new Bot(
                tile,
                this.behavior.copyWitchChange(0, 0.2),
                Direction.getRandom(random),
                this.color,
                minEnergyReproduction,
                NumRangeController.setInRange(this.predation + random.nextInt(-mutationSpread, mutationSpread), 0, 10),
                NumRangeController.setInRange(this.photosynthesis + random.nextInt(-mutationSpread, mutationSpread), 0, 10),
                NumRangeController.setInRange(this.soil + random.nextInt(-mutationSpread, mutationSpread), 0, 10)
        );

        this.updatableObjectNode.registerNewObject(newBot);
        this.energy -= minEnergyReproduction;
    }

    public void tryProduceNewBot(int index){
        Tile tile = getTileInVisibleArea(index);
        if(tile.getAbstractTileObject() == null && this.energy > minEnergyReproduction) produceNewBot(tile);
    }

    public void die(){
        for(int i = 0; i < 9; i++) getTileInVisibleArea(i).changeSoil(residualEnergyInSoil);
        delete();
    }

    public void delete(){
        this.tile.setAbstractTileObject(null);
        this.tile = null;
        this.updatableObjectNode.unRegisterObject();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getPredation() {
        return predation;
    }

    public void setPredation(int predation) {
        this.predation = NumRangeController.setInRange(predation, 0, 10);
    }

    public int getPhotosynthesis() {
        return photosynthesis;
    }

    public void setPhotosynthesis(int photosynthesis) {
        this.photosynthesis = NumRangeController.setInRange(photosynthesis, 0, 10);
    }

    public int getSoil() {
        return soil;
    }

    public void setSoil(int soil) {
        this.soil = NumRangeController.setInRange(soil, 0, 10);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = NumRangeController.setInRange(energy, 0, maxEnergy);
    }

    // TODO: добавить стандартные методы
}
