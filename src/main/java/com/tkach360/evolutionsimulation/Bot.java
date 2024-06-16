package com.tkach360.evolutionsimulation;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

/** класс бота
 * основной класс проекта*/
public class Bot extends AbstractTileObject{

    /** количество энергии которое тратится за один тик */
    public static int energyPerTik = 5;
    /** максимально возможное количество энергии бота */
    public static int maxEnergy = 100;
    /** энергия, оставляемая ботом в почве после смерти */
    public static int residualEnergyInSoil = 1;
    /** минимальная энергия необходимая для размножения, она передается потомку */
    public static int minEnergyReproduction = 50;
    /** начальное количество энергии бота */
    public static int defaultEnergy = minEnergyReproduction;
    /** показатель того, на сколько параметры потомка могут отличаться от параметров родителя */
    public static int mutationSpread = 1;

    /** устанавливается если последним источником энергии бота был фотосинтез */
    public static final Color PHOTOSYNTHESIS_COLOR = Color.rgb(0, 210, 0);
    /** устанавливается если последним источником энергии бота было хищничество */
    public static final Color PREDATION_COLOR = Color.rgb(210, 0, 0);
    /** устанавливается если последним источником энергии бота была энергия почвы */
    public static final Color SOIL_COLOR = Color.rgb(0, 0, 210);

    /** ссылка на узел в botsController, устанавливается при вызове IBotsController.addBot() */
    private BotNode botNode;

    private final AbstractBehavior behavior;
    private Direction direction;
    private Color color; // бот получает цвет в зависимости от последнего источника энергии
    private int energy;
    private int predation;
    private int photosynthesis;
    private int soil;

    public Bot(Tile tile, AbstractBehavior behavior, Direction direction, Color color, int energy, int predation, int photosynthesis, int soil) {
        this.tile = tile;
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
        this.tile = tile;
        this.behavior = new TestBehavior(); //TODO изменить при дорпботке механизма неследования поведения
        this.direction = Direction.getRandom(random);
        this.color = PHOTOSYNTHESIS_COLOR;
        this.energy = defaultEnergy;
        this.predation = random.nextInt(10);
        this.photosynthesis = random.nextInt(10);
        this.soil = random.nextInt(10);

        tile.setAbstractTileObject(this);
    }

    public void moveForward(){
        Tile tileForward = getTileInVisibleArea(1);
        if(tileForward.getAbstractTileObject() == null){
            setTile(tileForward);
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

    /** возвращает список тайлов поблизости
     * среди этих тайлов нет передаваемого тайла */
    public ArrayList<Tile> getTilesNear(Tile thisTile){
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
        behavior.doSomething(this);
        updateState();
    }

    public void photosynthesize(){
        changeEnergy(getEnergyFromSource(this.tile.getLighting(), photosynthesis));
    }

    public void consumeSoil(){
        changeEnergy(getEnergyFromSource(this.tile.getSoilEnergy(), soil));
        this.tile.setSoilEnergy(0);
    }

    public void eatBot(Bot bot){
        changeEnergy(getEnergyFromSource(bot.getEnergy() / 5, predation));
        bot.die();
    }

    private int getEnergyFromSource(int source, int efficiency){
        return (source + 1) * efficiency;
    }

    private void changeEnergy(int delta){
        setEnergy(this.energy + delta);
    }

    private void updateState(){
        changeEnergy(-energyPerTik);
        if(this.energy == 0) die();
    }

    // TODO: нужно доделать механику размножения с учетом алгоритма поведения
    public void produceNewBot(Tile tile){
        Random random = new Random();

        Bot newBot = new Bot(
                tile,
                this.behavior,
                Direction.getRandom(random),
                this.color,
                minEnergyReproduction,
                this.predation + random.nextInt(-mutationSpread, mutationSpread),
                this.photosynthesis + random.nextInt(-mutationSpread, mutationSpread),
                this.soil + random.nextInt(-mutationSpread, mutationSpread)
        );

        this.botNode.registerNewBot(newBot);
    }

    public void die(){
        for(int i = 0; i < 9; i++) getTileInVisibleArea(i).changeSoil(residualEnergyInSoil);
        delete();
    }

    public void delete(){
        this.tile.setAbstractTileObject(null);
        this.tile = null;
        this.botNode.unRegisterBot();
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile.setAbstractTileObject(null);
        this.tile = tile;
        tile.setAbstractTileObject(this);
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
        this.predation = NumRangeController.setInRange(predation, 0, 4);
    }

    public int getPhotosynthesis() {
        return photosynthesis;
    }

    public void setPhotosynthesis(int photosynthesis) {
        this.photosynthesis = NumRangeController.setInRange(photosynthesis, 0, 4);
    }

    public int getSoil() {
        return soil;
    }

    public void setSoil(int soil) {
        this.soil = NumRangeController.setInRange(soil, 0, 4);;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BotNode getBotNode() {
        return botNode;
    }

    public void setBotNode(BotNode botNode) {
        this.botNode = botNode;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = NumRangeController.setInRange(energy, 0, maxEnergy);
    }

    // TODO: добавить стандартные методы
}
