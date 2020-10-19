package steve.step_definitions;

class TextBlobGenerator {
    private String blob = "";
    private static final String steve;

    static {
        steve = "mcdonald";
    }

    private TextBlobGenerator(String text, char[] letters) {
        int addLettersFor;
        int padLeftFor;
        int padRightFor;
        String paddingStyle;
        String[] textArray = text.split(",");

        if (textArray.length == 4) {
            paddingStyle = textArray[3];
        } else {
            paddingStyle = " ";
        }

        addLettersFor = numberOfIterations(textArray[0]);
        padLeftFor = numberOfIterations(textArray[1]);
        padRightFor = numberOfIterations(textArray[2]);

        if (paddingStyle.contains(" ")) {
            configureBlob(paddingStyle, addLettersFor, letters, 0);
        } else if (paddingStyle.contains("left")) {
            configureBlob(paddingStyle, addLettersFor, letters, padLeftFor);
        } else if (paddingStyle.contains("right")) {
            configureBlob(paddingStyle, addLettersFor, letters, padRightFor);
        }
    }

    private String getBlob() {
        return blob;
    }

    private int numberOfIterations(String textFor) {
        int posColon = textFor.indexOf(":") + 1;
        int posDash = textFor.indexOf("-");

        return Integer.parseInt(textFor.substring(posColon, posDash));
    }

    private void configureBlob(String paddingStyle, int addLettersFor, char[] letters, int padFor) {
        boolean completed;
        int currentCounter;
        blob = "";

        completed = false;
        while (!completed) {
            if (addLettersFor > letters.length) {
                currentCounter = letters.length;
                addLettersFor -= letters.length;
            } else {
                currentCounter = addLettersFor;
                completed = true;
            }

            for (int i = 0; i < currentCounter; i++) {
                blob += letters[i];
            }
        }

        for (int i = 0; i < padFor; i++) {
            if (paddingStyle.contains("left")) {
                blob = "-" + blob;
            } else if (paddingStyle.contains("right")) {
                blob += "-";
            }
        }
    }

    public static void main(String[] args) {
        char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        String text;

        text = "addLettersFor:9-loops,padLeftFor:99-loops,padRightFor:999-loops,paddingStyle:left";
        TextBlobGenerator blob1 = new TextBlobGenerator(text, letters);
        System.out.println(blob1.getBlob());
        System.out.println(blob1.blob);

        text = "addLettersFor:3-loops,padLeftFor:5-loops,padRightFor:6-loops,paddingStyle:left";
        TextBlobGenerator blob2 = new TextBlobGenerator(text, letters);
        System.out.println(blob2.getBlob());
        System.out.println(blob2.blob);

        text = "addLettersFor:8-loops,padLeftFor:5-loops,padRightFor:7-loops,paddingStyle:right";
        TextBlobGenerator blob3 = new TextBlobGenerator(text, letters);
        System.out.println(blob3.getBlob());
        System.out.println(blob3.blob);

        text = "addLettersFor:8-loops,padLeftFor:5-loops,padRightFor:7-loops";
        TextBlobGenerator blob4 = new TextBlobGenerator(text, letters);
        System.out.println(blob4.getBlob());
        System.out.println(blob4.blob);

        System.out.println(new TextBlobGenerator(text, letters).getBlob());
        System.out.println(new TextBlobGenerator(text, letters).getBlob());

        System.out.println(blob1.steve);
        System.out.println(blob2.steve);
        System.out.println(blob3.steve);
        System.out.println(blob4.steve);
    }
}