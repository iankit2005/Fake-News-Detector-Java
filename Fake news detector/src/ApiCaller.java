public class ApiCaller {
    public static String checkHeadline(String headline) {


        try {

            if (headline.toLowerCase().contains("earth is flat")) {
                return "FAKE NEWS: This claim has been debunked.";
            } else if (headline.toLowerCase().contains("covid vaccine")) {
                return "TRUE NEWS: Verified by multiple sources.";
            }
            else{
                return "COULD NOT VERIFY: Not found in database.";
            }
        } catch (Exception e) {
            return "Error occurred while checking headline. Please try again later.";
        }



    }
}
