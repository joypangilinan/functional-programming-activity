package functional.programming.activity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {

        Stream<String> cebu = Files.lines(Paths.get("SalesBranches","cebu.csv"));
        Stream<String> manila = Files.lines(Paths.get("SalesBranches","manila.csv"));
        Stream<String> davao = Files.lines(Paths.get("SalesBranches","davao.csv"));


        Sales sale = new Sales();
        sale.listAll(cebu,manila,davao);
        sale.printAll();
        sale.displayCebuSales();
        sale.displayManilaSales();
        sale.displayDavaoSales();
        sale.displayOverAllSales();
        sale.displayYear2016();
        sale.displayMonthSoldTheMostInFruits();
        sale.displaySoldIn2012();
        sale.displayMonthSoldTheMost();
        sale.displayNameMostSoldInManila();
        sale.displayNameMostSoldInCebu();
        sale.displayNameMostSoldInDavao();
    }
}

