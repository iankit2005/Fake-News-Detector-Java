public class ApiCaller {
    public static String checkHeadline(String headline) {
        if (headline.toLowerCase().contains("earth is flat")) {
            return "FAKE NEWS: This claim has been debunked.";
        } else if (headline.toLowerCase().contains("covid vaccine")) {
            return "TRUE NEWS: Verified by multiple sources.";
        }
        else{
            return "COULD NOT VERIFY: Not found in database.";
        }
    }
}
