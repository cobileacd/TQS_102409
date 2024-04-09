package ua.pt.ChronoCharm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ua.pt.ChronoCharm.data.Watch;
import ua.pt.ChronoCharm.data.WatchRepository;

@Component
public class SampleDataInitializer implements CommandLineRunner {

    private final WatchRepository watchRepository;

    public SampleDataInitializer(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        watchRepository.save(new Watch("Tudor", "Pelagos 39", "9123908", "Automatic", "39", 2023, 4150));
        watchRepository.save(new Watch("Rolex", "Submariner", "8123881", "Automatic", "40", 2000, 11500));
        watchRepository.save(new Watch("Omega", "Speedmaster Professional", "311.30.42.30.01.005", "Manual", "42", 1969, 5250));
        watchRepository.save(new Watch("Patek Philippe", "Nautilus", "5711/1A-010", "Automatic", "40", 2006, 31500));
        watchRepository.save(new Watch("Audemars Piguet", "Royal Oak", "15202ST.OO.1240ST.01", "Automatic", "39", 1972, 45500));
        watchRepository.save(new Watch("Tag Heuer", "Carrera", "CBN2A10.BA0643", "Automatic", "44", 1963, 6450));
        watchRepository.save(new Watch("Breitling", "Navitimer", "AB0127211C1A1", "Automatic", "43", 1952, 8650));
        watchRepository.save(new Watch("IWC", "Portugieser", "IW500701", "Automatic", "42", 1998, 13500));
        watchRepository.save(new Watch("Hublot", "Big Bang", "411.NX.1170.RX", "Automatic", "45", 2005, 19500));
        watchRepository.save(new Watch("Cartier", "Santos", "WSSA0029", "Automatic", "39", 1904, 7450));
        watchRepository.save(new Watch("Seiko", "Alpinist", "SARB017", "Automatic", "38", 2006, 650));
        watchRepository.save(new Watch("Panerai", "Luminor", "PAM00561", "Manual", "44", 1950, 7650));
        watchRepository.save(new Watch("Longines", "Heritage Classic", "L28214933", "Automatic", "38", 2019, 2200));
        watchRepository.save(new Watch("Zenith", "Defy Classic", "95.9000.670/78.M9000", "Automatic", "41", 1969, 5850));
        watchRepository.save(new Watch("Chopard", "Mille Miglia", "168589-3001", "Automatic", "44", 1988, 6950));
        watchRepository.save(new Watch("Jaeger-LeCoultre", "Reverso", "Q2538120", "Manual", "41", 1931, 9250));
        watchRepository.save(new Watch("Oris", "Divers Sixty-Five", "01 733 7707 4354-07 8 20 17", "Automatic", "40", 1965, 1750));
        watchRepository.save(new Watch("Montblanc", "Heritage Chronométrie", "112515", "Automatic", "39", 2017, 3250));
        watchRepository.save(new Watch("Breguet", "Classique", "5177BB/29/9V6", "Manual", "38", 1999, 18950));
        watchRepository.save(new Watch("Girard-Perregaux", "Laureato", "81000-11-634-11A", "Automatic", "42", 1975, 8850));
        watchRepository.save(new Watch("Bell & Ross", "BR 03-92", "BR0392-BL-ST/SCA", "Automatic", "42", 1992, 3950));
        watchRepository.save(new Watch("Ulysse Nardin", "Marine Torpilleur", "1183-320LE/62", "Automatic", "44", 2007, 6450));
        watchRepository.save(new Watch("Maurice Lacroix", "Aikon", "AI6008-SS002-430-1", "Automatic", "42", 1994, 2250));
        watchRepository.save(new Watch("Hermès", "Arceau", "037961WW00", "Automatic", "40", 1978, 7450));
        watchRepository.save(new Watch("Rado", "True Thinline", "R27058902", "Quartz", "39", 2008, 1850));
        watchRepository.save(new Watch("Grand Seiko", "Spring Drive", "SBGA211", "Automatic", "41", 2010, 4950));
        watchRepository.save(new Watch("Tissot", "PRC 200", "T0554101104700", "Quartz", "42", 1953, 550));
        watchRepository.save(new Watch("Nomos Glashütte", "Tangente", "101", "Manual", "38", 1990, 2450));
        watchRepository.save(new Watch("Bulgari", "Octo Finissimo", "103011", "Automatic", "40", 2009, 12950));
        watchRepository.save(new Watch("Sinn", "U1", "1010.020", "Automatic", "44", 1961, 2950));
        watchRepository.save(new Watch("Chopard", "Happy Sport", "278509-6002", "Quartz", "36", 1993, 8950));
        watchRepository.save(new Watch("Seiko", "Presage", "SPB041J1", "Automatic", "40", 1988, 850));
        watchRepository.save(new Watch("Mido", "Multifort", "M005.430.11.031.80", "Automatic", "42", 1926, 950));
        watchRepository.save(new Watch("Bremont", "MBII", "MBII/OR", "Automatic", "43", 2002, 4450));
        watchRepository.save(new Watch("Citizen", "Eco-Drive", "AW7030-06E", "Quartz", "41", 1982, 350));
        watchRepository.save(new Watch("IWC", "Pilot's Watch", "IW387901", "Automatic", "43", 1992, 7950));
        watchRepository.save(new Watch("Raymond Weil", "Maestro", "2237-ST-00659", "Automatic", "40", 1976, 1650));
        watchRepository.save(new Watch("Hamilton", "Khaki Field", "H70535081", "Automatic", "42", 1940, 450));
        watchRepository.save(new Watch("Junghans", "Max Bill", "027/4700.00", "Automatic", "38", 1956, 1250));
        watchRepository.save(new Watch("Victorinox", "Maverick", "241698.1", "Quartz", "43", 1993, 650));
        watchRepository.save(new Watch("Fossil", "Gen 5", "FTW4024", "Smartwatch", "44", 2019, 295));
        watchRepository.save(new Watch("Frederique Constant", "Classics", "FC-303WN5B6B", "Automatic", "40", 1988, 1450));
        watchRepository.save(new Watch("Alpina", "AlpinerX", "AL-283LBBW5AQ6", "Quartz", "45", 2009, 950));
        watchRepository.save(new Watch("Glashütte Original", "Senator", "1-39-52-06-02-04", "Automatic", "40", 1845, 4950));
        watchRepository.save(new Watch("Tissot", "Everytime", "T109.407.11.032.00", "Quartz", "42", 1853, 295));
        watchRepository.save(new Watch("Zenith", "Elite", "03.2020.670/01.C498", "Automatic", "40", 1865, 3750));
        watchRepository.save(new Watch("Citizen", "Promaster", "BN0190-07E", "Eco-Drive", "42", 1918, 250));
        watchRepository.save(new Watch("Vacheron Constantin", "Overseas", "4500V/110A-B146", "Automatic", "41", 1755, 18500));
        watchRepository.save(new Watch("Orient", "Bambino", "RA-AC0002S10A", "Automatic", "40", 1950, 350));
        watchRepository.save(new Watch("Bell & Ross", "BR 05", "BR05A-BL-ST/SST", "Automatic", "42", 1992, 4950));
        watchRepository.save(new Watch("Rado", "True Square", "R27069159", "Quartz", "38", 1917, 1200));
        watchRepository.save(new Watch("G-Shock", "DW5600BB-1", "DW5600BB-1", "Quartz", "43", 1983, 99));
        watchRepository.save(new Watch("Seiko", "Prospex", "SRPE03K1", "Automatic", "42", 1881, 350));
        watchRepository.save(new Watch("Ball", "Engineer III", "NM2188C-S5J-WH", "Automatic", "40", 1891, 1550));
        watchRepository.save(new Watch("Certina", "DS Action", "C032.407.11.041.00", "Quartz", "43", 1888, 550));
        watchRepository.save(new Watch("Tudor", "Black Bay", "M79540-0005", "Automatic", "41", 1926, 3450));
        watchRepository.save(new Watch("Maurice Lacroix", "Pontos", "PT6158-SS001-23E-1", "Automatic", "42", 1975, 2150));
        watchRepository.save(new Watch("Bulova", "Precisionist", "98B229", "Quartz", "44", 1875, 350));
        watchRepository.save(new Watch("Mido", "Baroncelli", "M8600.4.26.1", "Automatic", "38", 1918, 650));
        watchRepository.save(new Watch("Seiko", "Presage", "SRPD39", "Automatic", "40", 1881, 450));
        watchRepository.save(new Watch("Sinn", "556 I", "556.010", "Automatic", "38", 1961, 1250));
        watchRepository.save(new Watch("Timex", "Weekender", "TW2T72900", "Quartz", "40", 1854, 40));
        watchRepository.save(new Watch("Junghans", "Max Bill", "027/4600.00", "Automatic", "38", 1861, 950));
        watchRepository.save(new Watch("Orient", "Sun and Moon", "RA-AK0402L10B", "Automatic", "42", 1950, 395));
        watchRepository.save(new Watch("Hamilton", "Jazzmaster", "H32515135", "Automatic", "42", 1892, 550));
        watchRepository.save(new Watch("Casio", "Edifice", "EFV100D-1AV", "Quartz", "42", 1946, 120));
        watchRepository.save(new Watch("Braun", "BN0035", "BN0035BKBKG", "Quartz", "38", 1983, 250));
        watchRepository.save(new Watch("Sekonda", "1948", "1618.27", "Quartz", "40", 1966, 90));
        watchRepository.save(new Watch("Victorinox", "Alliance", "241476", "Quartz", "41", 1989, 350));
        watchRepository.save(new Watch("Emporio Armani", "AR1971", "AR1971", "Quartz", "43", 1975, 150));
        watchRepository.save(new Watch("Garmin", "Fenix 6", "010-02158-14", "Smartwatch", "47", 1989, 650));
        watchRepository.save(new Watch("Skagen", "Ancher", "SKW6085", "Quartz", "40", 1989, 120));
        watchRepository.save(new Watch("Movado", "Museum Classic", "0606502", "Quartz", "40", 1881, 350));
        watchRepository.save(new Watch("Fossil", "FB-01", "FS5653", "Quartz", "42", 1984, 95));
        watchRepository.save(new Watch("Casio", "G-Shock", "GA2100-1A1", "Quartz", "44", 1983, 100));
        watchRepository.save(new Watch("Casio", "Wave Ceptor", "WV200A-1AV", "Quartz", "40", 1946, 50));
        watchRepository.save(new Watch("Suunto", "Core", "SS014809000", "Quartz", "49", 1936, 200));
        watchRepository.save(new Watch("Nixon", "Time Teller", "A045-511-00", "Quartz", "40", 1997, 60));
        watchRepository.save(new Watch("Fossil", "Gen 4 Explorist", "FTW4016", "Smartwatch", "45", 1984, 250));
        watchRepository.save(new Watch("Diesel", "On Full Guard 2.5", "DZT9001", "Smartwatch", "47", 1978, 275));
        watchRepository.save(new Watch("Casio", "Classic", "A168WEM-1", "Quartz", "37", 1946, 20));
        watchRepository.save(new Watch("Michael Kors", "Lexington", "MK8286", "Quartz", "45", 1981, 125));
        watchRepository.save(new Watch("Samsung", "Galaxy Watch 4", "SM-R870", "Smartwatch", "40", 1975, 300));
        watchRepository.save(new Watch("Tommy Hilfiger", "Sophisticated Sport", "1791483", "Quartz", "44", 1985, 90));
        watchRepository.save(new Watch("Armitron", "Dress Sport", "20/5207SVSV", "Quartz", "41", 1956, 35));
        watchRepository.save(new Watch("Guess", "Catalina", "W0979L1", "Quartz", "37", 1981, 75));
        watchRepository.save(new Watch("Timex", "Ironman", "TW5M24300", "Quartz", "43", 1854, 60));
        watchRepository.save(new Watch("Michael Kors", "Access Gen 4 Sofie", "MKT5045", "Smartwatch", "42", 1981, 275));
        watchRepository.save(new Watch("Skagen", "Grenen", "SKW6258", "Quartz", "38", 1989, 95));
        watchRepository.save(new Watch("Fossil", "The Minimalist", "FS5447", "Quartz", "44", 1984, 75));
        watchRepository.save(new Watch("Diesel", "Axial", "DZT2032", "Smartwatch", "48", 1978, 295));
    }
}
