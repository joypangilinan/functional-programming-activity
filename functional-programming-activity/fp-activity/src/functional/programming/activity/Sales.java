package functional.programming.activity;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sales {

    private static List<String> list = new ArrayList<>();
    SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat date1 = new SimpleDateFormat("MMMM/dd/yyyy");

    public void displayNameMostSoldInDavao() throws IOException {
        Stream<String> davao = Files.lines(Paths.get("SalesBranches", "davao.csv"));
        Map<BigDecimal, String> allSoldDavao = new TreeMap<>();
        Map<String, BigDecimal> finalResult = new TreeMap<>();

        List<String> davaoList = davao.sorted()
                .collect(Collectors.toList());

        davaoList.forEach(x -> {
            String[] lists = x.split(",");
            allSoldDavao.put(new BigDecimal(lists[2]), lists[0]);
        });

        allSoldDavao.forEach((key, value) -> {
            BigDecimal oldValue = finalResult.get(value) != null ? finalResult.get(value) : BigDecimal.ZERO;
            finalResult.put(value, oldValue.add(key));
        });

        String name = finalResult.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();
        System.out.println("Most sold item in davao: " + name);

    }

    public void displayNameMostSoldInCebu() throws IOException {
        Stream<String> cebu = Files.lines(Paths.get("SalesBranches", "cebu.csv"));
        Map<BigDecimal, String> allSoldCebu = new TreeMap<>();
        Map<String, BigDecimal> finalResult = new TreeMap<>();

        List<String> cebuList = cebu.sorted()
                .collect(Collectors.toList());

        cebuList.forEach(x -> {
            String[] lists = x.split(",");
            allSoldCebu.put(new BigDecimal(lists[2]), lists[0]);
        });

        allSoldCebu.forEach((key, value) -> {
            BigDecimal oldValue = finalResult.get(value) != null ? finalResult.get(value) : BigDecimal.ZERO;
            finalResult.put(value, oldValue.add(key));
        });

        String name = finalResult.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();
        System.out.println("Most sold item in cebu: " + name);

    }

    public void displayNameMostSoldInManila() throws IOException {

        Stream<String> manila = Files.lines(Paths.get("SalesBranches", "manila.csv"));
        Map<BigDecimal, String> allSoldManila = new TreeMap<>();
        Map<String, BigDecimal> finalResult = new TreeMap<>();

        List<String> manilaList = manila.sorted()
                .collect(Collectors.toList());

        manilaList.forEach(x -> {
            String[] lists = x.split(",");
            allSoldManila.put(new BigDecimal(lists[2]), lists[0]);
        });

        allSoldManila.forEach((key, value) -> {
            BigDecimal oldValue = finalResult.get(value) != null ? finalResult.get(value) : BigDecimal.ZERO;
            finalResult.put(value, oldValue.add(key));
        });

        String name = finalResult.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();
        System.out.println("Most sold item in manila: " + name);

    }

    public void displayMonthSoldTheMost() {
        Map<String, BigDecimal> allSold = new TreeMap<>();
        list.forEach(x -> {
            String[] lists = x.split(",");
            Date dDate = null;
            try {
                dDate = date.parse(lists[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            allSold.put(date1.format(dDate), new BigDecimal(lists[2]));
        });

        Map<String, BigDecimal> allSoldResult = new TreeMap<>();
        allSold.forEach((key, value) -> {
            String keys = key.split("/")[0];
            BigDecimal oldValue = allSoldResult.get(keys) != null ? allSoldResult.get(keys) : BigDecimal.ZERO;
            allSoldResult.put(keys, oldValue.add(value));
        });

        String month = allSoldResult.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();

        System.out.println("Month(Over all): " + month);

    }

    public void displaySoldIn2012() {
        Map<BigDecimal, String> result = new TreeMap<>();
        Map<String, BigDecimal> finalResult = new TreeMap<>();

        list.forEach(x -> {
            String[] lists = x.split(",");
            if (x.contains("2012")) {
                result.put(new BigDecimal(lists[2]), lists[0]);

            }
        });
        result.forEach((key, value) -> {
            BigDecimal oldValue = finalResult.get(value) != null ? finalResult.get(value) : BigDecimal.ZERO;
            finalResult.put(value, oldValue.add(key));
        });

        String name = finalResult.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();
        System.out.println("Most sold item in 2012: " + name);
    }

    public void displayMonthSoldTheMostInFruits() {
        Map<String, BigDecimal> fruit = new TreeMap<>();

        list.forEach(x -> {
            String[] lists = x.split(",");
            if (x.contains("Fruits")) {
                Date dDate = null;
                try {
                    dDate = date.parse(lists[1]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                fruit.put(date1.format(dDate), new BigDecimal(lists[2]));
            }
        });

        Map<String, BigDecimal> result = new TreeMap<>();
        fruit.forEach((key, value) -> {
            String keys = key.split("/")[0];
            BigDecimal oldValue = result.get(keys) != null ? result.get(keys) : BigDecimal.ZERO;
            result.put(keys, oldValue.add(value));
        });

        String month = result.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();

        System.out.println("Month(Fruits): " + month);


    }

    public void displayYear2016() {
        List<BigDecimal> dates = new ArrayList<>();

        list.forEach(x -> {
            String[] lists = x.split(",");
            if (x.contains("2016")) {
                BigDecimal sold = new BigDecimal(lists[2]).multiply(new BigDecimal(lists[3]));
                dates.add(sold);
            }

        });
        BigDecimal totals = dates.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Total Sales for the year 2016: " + totals);

    }

    public void displayOverAllSales() {
        List<BigDecimal> overAllTotal = new ArrayList<>();
        list.forEach(x -> {
            String[] lists = x.split(",");
            BigDecimal sold = new BigDecimal(lists[2]).multiply(new BigDecimal(lists[3]));
            overAllTotal.add(sold);
        });
        BigDecimal totals = overAllTotal.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Over All Sales: " + totals);
    }

    public void displayDavaoSales() throws IOException {
        List<BigDecimal> davaoTotal = new ArrayList<>();
        Stream<String> davao = Files.lines(Paths.get("SalesBranches", "davao.csv"));

        collect(davao).forEach(x -> {
            String[] lists = x.split(",");
            BigDecimal sold = new BigDecimal(lists[2]).multiply(new BigDecimal(lists[3]));
            davaoTotal.add(sold);
        });

        BigDecimal totals = davaoTotal.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Davao Sales: " + totals);

    }

    public void displayManilaSales() throws IOException {
        List<BigDecimal> manilaTotal = new ArrayList<>();
        Stream<String> manila = Files.lines(Paths.get("SalesBranches", "manila.csv"));

        collect(manila).forEach(x -> {
            String[] lists = x.split(",");
            BigDecimal sold = new BigDecimal(lists[2]).multiply(new BigDecimal(lists[3]));
            manilaTotal.add(sold);
        });

        BigDecimal totals = manilaTotal.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Manila Sales: " + totals);
    }

    public void displayCebuSales() throws IOException {

        Stream<String> cebu = Files.lines(Paths.get("SalesBranches", "cebu.csv"));
        List<BigDecimal> cebuTotal = new ArrayList<>();

        collect(cebu).forEach(x -> {
            String[] lists = x.split(",");
            BigDecimal sold = new BigDecimal(lists[2]).multiply(new BigDecimal(lists[3]));
            cebuTotal.add(sold);
        });

        BigDecimal totals = cebuTotal.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Cebu Sales: " + totals);
    }

    public List<String> collect(Stream<String> branches) {
        return branches.sorted().collect(Collectors.toList());
    }

    public void printAll() {
        TreeSet<String> names1 = new TreeSet<>();
        TreeSet<String> names = list.stream()
                .collect(Collectors.toCollection(TreeSet::new));
        names.forEach(x -> {
            String[] lists = x.split(",");
            names1.add(lists[0]);
        });
        System.out.println("LIST OF ALL ITEMS SOLD");
        names1.forEach(System.out::println);
        System.out.println();
    }

    public void listAll(Stream<String> cebu, Stream<String> manila, Stream<String> davao) {
        Stream<String> branchesAll = Stream.of(cebu, manila, davao)
                .reduce(Stream::concat)
                .orElseGet(Stream::empty);

        list = branchesAll
                .collect(Collectors.toList());
    }
}

