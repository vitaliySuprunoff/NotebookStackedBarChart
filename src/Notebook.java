import javafx.beans.property.SimpleStringProperty;

public class Notebook {
    private SimpleStringProperty ID;
    private SimpleStringProperty Creator;
    private SimpleStringProperty Frequency;
    private SimpleStringProperty RAM;
    public Notebook(String id, String creator, String frequency, String ram) {
            this.ID = new SimpleStringProperty(id);
            this.Creator = new SimpleStringProperty(creator);
            this.Frequency = new SimpleStringProperty(frequency);
            this.RAM = new SimpleStringProperty(ram);
        }
    public String getID() {
        return ID.get();
    }
    public void setID(String id) {
        ID.set(id);
    }
    public String getCreator() {
        return Creator.get();
    }
    public void setCreator(String creator) {
        Creator.set(creator);
    }
    public String getFrequency() {
        return Frequency.get();
    }
    public void setFrequency(String frequency) {
        Frequency.set(frequency);
    }
    public String getRAM() {
        return RAM.get();
    }
    public void setRAM(String ram) {
        Frequency.set(ram);
    }
    public String toString(){
        return getID()+"&"+getCreator()+"&"+getFrequency()+"&"+getRAM();
    }
}