package com.tkach360.evolutionsimulation;

import javafx.scene.paint.Color;

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

    private BotNode botNode;

    private int energy;
    private Color color; // бот получает цвет в зависимости от последнего источника энергии
    private AbstractBehavior behavior;
    private VisibleArea visibleArea;
    private int predation;
    private int photosynthesis;
    private int soil;

    public Bot(Tile tile, int[][] direction, int predation, int photosynthesis, int soil) {
        this.behavior = new TestBehavior(this);//
        this.energy = defaultEnergy;
        this.tile = tile;
        tile.setAbstractTileObject(this);
        this.visibleArea = new VisibleArea(direction);
        this.predation = NumRangeController.setInRange(predation, 0, 4);
        this.photosynthesis = NumRangeController.setInRange(photosynthesis, 0, 4);
        this.soil = NumRangeController.setInRange(soil, 0, 4);
        this.color = PHOTOSYNTHESIS_COLOR;
    }

    public Bot(Tile tile, Random random){
        this.behavior = new TestBehavior(this);//
        this.energy = defaultEnergy;
        this.tile = tile;
        this.visibleArea = new VisibleArea(random);
        tile.setAbstractTileObject(this);
        this.predation = random.nextInt(5);
        this.photosynthesis = random.nextInt(5);
        this.soil = random.nextInt(5);
        this.color = PHOTOSYNTHESIS_COLOR;
    }

    public void moveForward(){
        Tile tileForward = visibleArea.getTileInVisibleArea(1, this.tile);
        if(tileForward.getAbstractTileObject() == null){
            setTile(tileForward);
        }
    }

    // TODO: этот метод отвечает за принятие решения о действии и собственно действии
    public void update(){
        behavior.doSomething();
        updateState();
    }

    public void rotate(int[][] visibleArea) {
        this.visibleArea = new VisibleArea(visibleArea);
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
        int newPredation = this.predation + random.nextInt(-mutationSpread, mutationSpread);
        int newPhotosynthesis = this.photosynthesis + random.nextInt(-mutationSpread, mutationSpread);
        int newSoil = this.soil + random.nextInt(-mutationSpread, mutationSpread);
        VisibleArea newVisibleArea = new VisibleArea(random);

        Bot newBot = new Bot(tile, newVisibleArea.getDirection(), newPredation, newPhotosynthesis, newSoil);
        newBot.setColor(this.color);
        //Bot newBot = new Bot(tile, random);
        this.botNode.registerNewBot(newBot);

        //this.botNode.registerNewBot(this);
    }

    public void die(){
        for(int i = 0; i < 9; i++) visibleArea.getTileInVisibleArea(i, this.tile).changeSoil(residualEnergyInSoil);
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

    public VisibleArea getVisibleArea() {
        return visibleArea;
    }

    public void setVisibleArea(VisibleArea visibleArea) {
        this.visibleArea = visibleArea;
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
