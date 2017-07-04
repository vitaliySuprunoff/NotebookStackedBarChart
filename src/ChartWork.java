import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

public class ChartWork {
    static public ObservableList <XYChart.Series<String,Number>> series = FXCollections.observableArrayList();

    static void reDate(ObservableList<Notebook> data){
        getSeriesNames(data);
        for (Notebook ntb:
             data){
            for (XYChart.Series<String, Number> serie:
                 series) {
                if (serie.getName().equals(ntb.getFrequency())){
                    serie.getData().add(new XYChart.Data<>(ntb.getCreator(),1));
                }
            }
        }
    }

    static void getSeriesNames (ObservableList<Notebook> data){
        series.clear();
        for(int i=0;i<data.size();i++){
            if (!has(data.get(i))){
                XYChart.Series nSeries = new XYChart.Series<String, Number>();
                nSeries.setName(data.get(i).getFrequency());
                series.add(nSeries);
                }
        }
}
    static public boolean has(Notebook nt){
        for (XYChart.Series<String,Number> serie:
             series) {
            if (nt.getFrequency().equals(serie.getName())){
                return true;
            }
        }
        return false;
    }
}