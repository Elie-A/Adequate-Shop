package utilities.dataGenerator;

import java.util.HashMap;

public class DataGenerator {

    private final static String alpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRTSUVWXYZ";
    private final static String alpha_numeric = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRTSUVWXYZ1234567890";
    private final static String alpha_numeric_special = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRTSUVWXYZ1234567890!@#$%^&*()";
    private final static String[] country_code = new String[]{"ABW", "AFG", "AGO", "AIA", "ALA", "ALB", "AND", "ARE", "ARG", "ARM", "ASM", "ATA", "ATF", "ATG", "AUS", "AUT", "AZE", "BDI", "BEL", "BEN",
            "BES", "BFA", "BGD", "BGR", "BHR", "BHS", "BIH", "BLM", "BLR", "BLZ", "BMU", "BOL", "BRA", "BRB", "BRN", "BTN", "BVT", "BWA", "CAF", "CAN",
            "CCK", "CHE", "CHL", "CHN", "CIV", "CMR", "COD", "COG", "COK", "COL","COM", "CPV", "CRI", "CUB", "CUW", "CXR", "CYM", "CYP", "CZE", "DEU",
            "DJI", "DMA", "DNK", "DOM", "DZA", "ECU", "EGY", "ERI", "ESH", "ESP", "EST", "ETH", "FIN", "FJI", "FLK", "FRA", "FRO", "FSM", "GAB", "GBR",
            "GEO", "GGY", "GHA", "GIB", "GIN", "GLP", "GMB", "GNB", "GNQ", "GRC", "GRD", "GRL", "GTM", "GUF", "GUM", "GUY", "HKG", "HMD", "HND", "HRV",
            "HTI", "HUN", "IDN", "IMN", "IND", "IOT", "IRL", "IRN", "IRQ", "ISL", "ISR", "ITA", "JAM", "JEY", "JOR", "JPN", "KAZ", "KEN", "KGZ", "KHM",
            "KIR", "KNA", "KOR", "KWT", "LAO", "LBN", "LBR", "LBY", "LCA", "LIE", "LKA", "LSO", "LTU", "LUX", "LVA", "MAC", "MAF", "MAR", "MCO", "MDA",
            "MDG", "MDV", "MEX", "MHL", "MKD", "MLI", "MLT", "MMR", "MNE", "MNG", "MNP", "MOZ", "MRT", "MSR", "MTQ", "MUS", "MWI", "MYS", "MYT", "NAM",
            "NCL", "NER", "NFK", "NGA", "NIC", "NIU", "NLD", "NOR", "NPL", "NRU", "NZL", "OMN", "PAK", "PAN", "PCN", "PER", "PHL", "PLW", "PNG", "POL",
            "RWA", "SAU", "SDN", "SEN", "SGP", "SGS", "SHN", "SJM", "SLB", "SLE", "PRI", "PRK", "PRT", "PRY", "PSE", "PYF", "QAT", "REU", "ROU", "RUS",
            "SLV", "SMR", "SOM", "SPM", "SRB", "SSD", "STP", "SUR", "SVK", "SVN", "SWE", "SWZ", "SXM", "SYC", "SYR", "TCA", "TCD", "TGO", "THA", "TJK",
            "TKL", "TKM", "TLS", "TON", "TTO", "TUN", "TUR", "TUV", "TWN", "TZA", "UGA", "UKR", "UMI", "URY", "USA", "UZB", "VAT", "VCT", "VEN", "VGB",
            "VIR", "VNM", "VUT", "WLF", "WSM", "YEM", "ZAF", "ZMB", "ZWE"};

    private static String generateName(int maxCharacters) {
        StringBuilder sb = new StringBuilder(maxCharacters);

        for (int i = 0; i < maxCharacters; i++) {
            int index = (int) (alpha.length() * Math.random());
            sb.append(alpha.charAt(index));
        }

        return sb.toString();
    }

    private static String generateEmail(int maxCharacters) {
        StringBuilder sb = new StringBuilder(maxCharacters);

        for (int i = 0; i < maxCharacters; i++) {
            int index = (int) (alpha_numeric.length() * Math.random());
            sb.append(alpha_numeric.charAt(index));
        }

        return sb.toString() + "@gmail.com";
    }

    private static String generatePassword(int maxCharacters) {
        StringBuilder sb = new StringBuilder(maxCharacters);

        for (int i = 0; i < maxCharacters; i++) {
            int index = (int) (alpha_numeric_special.length() * Math.random());
            sb.append(alpha_numeric_special.charAt(index));
        }

        return sb.toString();
    }

    private static String getRandomCountryCode(){
        int randomNumber = (int) ((Math.random() * (country_code.length)) + 0);
        return country_code[randomNumber];
    }

    public static HashMap<String, String> generateRegistrationData(int maxName, int maxEmail, int maxPassword) {
        HashMap<String, String> map = new HashMap<>();

        map.put("name", generateName(maxName));
        map.put("email", generateEmail(maxEmail));
        map.put("password", generatePassword(maxPassword));

        return map;
    }

    public static HashMap<String, String> generateUserCreationData(int maxName, int maxEmail) {
        HashMap<String, String> map = new HashMap<>();

        map.put("name", generateName(maxName));
        map.put("email", generateEmail(maxEmail));
        map.put("location", getRandomCountryCode());

        return map;
    }
}
