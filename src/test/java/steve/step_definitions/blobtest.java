//package steve.step_definitions;
//
//public class blobtest {
//
//    public static void main(String[] args) {
//        char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
//        String text;
//
//        text = "addLettersFor:9-loops,padLeftFor:99-loops,padRightFor:999-loops,paddingStyle:left";
//        TextBlobGenerator blob1 = new TextBlobGenerator(text, letters);
//        System.out.println(blob1.getBlob());
//        System.out.println(blob1.blob);
//
//        text = "addLettersFor:3-loops,padLeftFor:5-loops,padRightFor:6-loops,paddingStyle:left";
//        TextBlobGenerator blob2 = new TextBlobGenerator(text, letters);
//        System.out.println(blob2.getBlob());
//        System.out.println(blob2.blob);
//
//        text = "addLettersFor:8-loops,padLeftFor:5-loops,padRightFor:7-loops,paddingStyle:right";
//        TextBlobGenerator blob3 = new TextBlobGenerator(text, letters);
//        System.out.println(blob3.getBlob());
//        System.out.println(blob3.blob);
//
//        text = "addLettersFor:8-loops,padLeftFor:5-loops,padRightFor:7-loops";
//        TextBlobGenerator blob4 = new TextBlobGenerator(text, letters);
//        System.out.println(blob4.getBlob());
//        System.out.println(blob4.blob);
//
//        System.out.println(new TextBlobGenerator(text, letters).getBlob());
//        System.out.println(new TextBlobGenerator(text, letters).getBlob());
//
//        System.out.println(blob1.steve);
//        System.out.println(blob2.steve);
//        System.out.println(blob3.steve);
//        System.out.println(blob4.steve);
//    }
//}