package steve.step_definitions;

import steve.FileOps;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileOpsTest {
    static final String TEMP_DIR = "c:\\temp_dir\\";

    FileOps fo = new FileOps();

    //    @Test
    public void getPathTest() {

        String s;

        s = fo.getPath ( "target" );

        Assert.assertEquals ( "C:/LGIM/workspaces/exprEngine/target", s );

        ///////////////////////////

        fo.deleteFile ( "target/temp.txt" );

        fo.writeFile ( "target/temp.txt", "TestEvidenceOut_2099_12_31_23_59_59" );

        s = fo.getPath ( "target/temp.txt" );

        Assert.assertEquals ( "C:/LGIM/workspaces/exprEngine/target/temp.txt", s );

        ///////////////////////////

        fo.deleteFile ( "target/this_file_does_not_exist.txt" );

        s = fo.getPath ( "target/this_file_does_not_exist.txt" );

        Assert.assertEquals ( "C:/LGIM/workspaces/exprEngine/target/this_file_does_not_exist.txt", s );


        ///////////////////////////

        fo.deleteFile ( "target/this_dir_does_not_exist" );

        s = fo.getPath ( "target/this_dir_does_not_exist" );

        Assert.assertEquals ( "C:/LGIM/workspaces/exprEngine/target/this_dir_does_not_exist", s );

    }

    public void makeDirTest() {

        String s;

        // basic test

        fo.deleteFile ( TEMP_DIR + "new_directory/new_file.txt" );
        fo.deleteFile ( TEMP_DIR + "new_directory" );

        fo.makeDir ( TEMP_DIR + "new_directory" );

        fo.writeFile ( TEMP_DIR + "new_directory/new_file.txt", "abc123" );

        s = fo.readFile ( TEMP_DIR + "new_directory/new_file.txt" );

        Assert.assertEquals ( "abc123", s );

        // clean up

        fo.deleteFile ( TEMP_DIR + "new_directory/new_file.txt" );
        fo.deleteFile ( TEMP_DIR + "new_directory" );

        // multi level

        fo.deleteFile ( TEMP_DIR + "new_directory/sub_dir/new_file.txt" );
        fo.deleteFile ( TEMP_DIR + "new_directory/sub_dir" );
        fo.deleteFile ( TEMP_DIR + "new_directory" );

        fo.makeDir ( TEMP_DIR + "new_directory" );
        fo.makeDir ( TEMP_DIR + "new_directory/sub_dir" );

        fo.writeFile ( TEMP_DIR + "new_directory/sub_dir/new_file.txt", "abc123" );

        s = fo.readFile ( TEMP_DIR + "new_directory/sub_dir/new_file.txt" );

        Assert.assertEquals ( "abc123", s );

        // clean up

        fo.deleteFile ( TEMP_DIR + "new_directory/sub_dir/new_file.txt" );
        fo.deleteFile ( TEMP_DIR + "new_directory/sub_dir" );
        fo.deleteFile ( TEMP_DIR + "new_directory" );

        // relative path


        fo.makeDir ( TEMP_DIR + "new_directory" );
        fo.makeDir ( TEMP_DIR + "new_directory/sub_dir" );
        fo.makeDir ( TEMP_DIR + "new_directory/sub_dir/../sub_dir2" );

        fo.writeFile ( TEMP_DIR + "new_directory/sub_dir2/new_file2.txt", "xyz789" );

        s = fo.readFile ( TEMP_DIR + "new_directory/sub_dir2/new_file2.txt" );

        Assert.assertEquals ( "xyz789", s );

        // no base dir

        fo.deleteFile ( TEMP_DIR + "new_directory/sub_dir/new_file.txt" );
        fo.deleteFile ( TEMP_DIR + "new_directory/sub_dir2/new_file2.txt" );
        fo.deleteFile ( TEMP_DIR + "new_directory/sub_dir" );
        fo.deleteFile ( TEMP_DIR + "new_directory/sub_dir2" );
        fo.deleteFile ( TEMP_DIR + "new_directory" );

        fo.deleteFile ( "new_directory/sub_dir/new_file.txt" );
        fo.deleteFile ( "new_directory/sub_dir" );
        fo.deleteFile ( "new_directory" );

        fo.makeDir ( "new_directory" );
        fo.makeDir ( "new_directory/sub_dir" );

        fo.writeFile ( "new_directory/sub_dir/new_file.txt", "abc123" );

        s = fo.readFile ( "C:/LGIM/workspaces/exprEngine/new_directory/sub_dir/new_file.txt" );

        Assert.assertEquals ( "abc123", s );

        // clean up

        fo.deleteFile ( TEMP_DIR + "new_directory/sub_dir/new_file.txt" );
        fo.deleteFile ( TEMP_DIR + "new_directory/sub_dir2/new_file2.txt" );
        fo.deleteFile ( TEMP_DIR + "new_directory/sub_dir" );
        fo.deleteFile ( TEMP_DIR + "new_directory/sub_dir2" );
        fo.deleteFile ( TEMP_DIR + "new_directory" );

        fo.deleteFile ( "new_directory/sub_dir/new_file.txt" );
        fo.deleteFile ( "new_directory/sub_dir" );
        fo.deleteFile ( "new_directory" );


    }

    //    @Test
    public void copyFileTest() {
        String s;

        fo.deleteFile ( TEMP_DIR + "test1.txt" );

        fo.writeFile ( TEMP_DIR + "test1.txt", "abc123" );

        fo.copyFile ( TEMP_DIR + "test1.txt", TEMP_DIR + "test2.txt" );

        s = fo.readFile ( TEMP_DIR + "test2.txt" );

        Assert.assertEquals ( "abc123", s );

        fo.copyFile ( TEMP_DIR + "file_does_not_exist.txt", TEMP_DIR + "test2.txt" );

        s = fo.readFile ( TEMP_DIR + "test2.txt" );

        Assert.assertEquals ( "abc123", s );
    }

    //    @Test
    public void countMatchesTest() {
        String s = "aaaxaaa aaa";
        int n = fo.countMatches ( s, "aaa" );
        Assert.assertEquals ( 3, n );

        s = "aaa,aaa,aaa\r\nbbb,bbb,bbb\r\n";
        n = fo.countMatches ( s, "," );
        Assert.assertEquals ( 4, n );

        s = "aaa,aaa,aaa\r\nbbb,bbb,bbb\r\n";
        n = fo.countMatches ( s, "x" );
        Assert.assertEquals ( 0, n );

        s = "";
        n = fo.countMatches ( s, "x" );
        Assert.assertEquals ( 0, n );

        s = null;
        n = fo.countMatches ( s, "x" );
        Assert.assertEquals ( 0, n );
    }

    //    @Test
    public void countFilesTest() {
        int n = fo.countFiles ( "C:/");

        Assert.assertTrue ( n > 10 );

        n = fo.countFiles ( "C:/thisdirectorydoesnotexist");

        Assert.assertTrue ( n == -1 );

    }

    @Test
    public void getFileNamesTest() {

        fo.deleteFiles ( TEMP_DIR );

        fo.writeFile ( TEMP_DIR + "bfile3.txt", "x" );
        fo.writeFile ( TEMP_DIR + "afile2.txt", "x" );
        fo.writeFile ( TEMP_DIR + "cfile1.txt", "x" );

        String[] a = fo.getFileNames1 ( TEMP_DIR, "file.*" );

        Assert.assertEquals ( "afile2.txt"   , a[0] );
        Assert.assertEquals ( "bfile3.txt"   , a[1] );
        Assert.assertEquals ( "cfile1.txt"   , a[2] );

    }

    //    @Test
    public void deleteFilesTest() {
        // happy path

        fo.deleteFile ( TEMP_DIR + "deleteFilesTest.txt" );

        fo.writeFile ( TEMP_DIR + "deleteFilesTest.txt", "xxx\n" );

        fo.deleteFiles ( TEMP_DIR );

        int n = fo.countFiles ( TEMP_DIR );

        Assert.assertEquals ( 0, n );

        // empty dir

        fo.deleteFiles ( TEMP_DIR );

        n = fo.countFiles ( TEMP_DIR );

        Assert.assertEquals ( 0, n );

        // missing dir

        fo.deleteFiles ( TEMP_DIR + "missing_dir" );

        n = fo.countFiles ( TEMP_DIR );

        Assert.assertEquals ( 0, n );

        // dir contains dir

        fo.deleteFiles ( TEMP_DIR );

        fo.makeDir ( TEMP_DIR + "new_dir" );

        fo.deleteFiles ( TEMP_DIR );

        n = fo.countFiles ( TEMP_DIR );

        Assert.assertEquals ( 0, n );

        // dir contains dir which contains sub dir

        fo.deleteFiles ( TEMP_DIR );

        fo.makeDir ( TEMP_DIR + "new_dir" );
        fo.makeDir ( TEMP_DIR + "new_dir/sub_dir" );

        fo.deleteFiles ( TEMP_DIR );

        n = fo.countFiles ( TEMP_DIR );

        Assert.assertEquals ( 1, n );

        // clean up

        fo.deleteFile ( TEMP_DIR + "new_dir/sub_dir" );
        fo.deleteFile ( TEMP_DIR + "new_dir" );

    }

    //    @Test
    public void readFileTest() {
        try {

            fo.deleteFiles ( TEMP_DIR );

            String s;
            FileWriter     fw = new FileWriter( TEMP_DIR + "file1.txt", true );
            BufferedWriter bw = new BufferedWriter( fw );

            // normal read

            bw.write ( "aaa" + FileOps.CRLF);
            bw.write ( "bbb" + FileOps.CRLF);
            bw.write ( "ccc" + FileOps.CRLF);
            bw.close();

            s = fo.readFile ( TEMP_DIR + "file1.txt" );

            Assert.assertEquals ( "aaa" + FileOps.CRLF + "bbb" + FileOps.CRLF + "ccc" + FileOps.CRLF, s );

            // missing file

            s = fo.readFile ( "xxx");

            Assert.assertEquals ( null, s );

            // large file

            fw = new FileWriter( TEMP_DIR + "file1.txt", false );
            bw = new BufferedWriter( fw );

            fo.deleteFiles ( TEMP_DIR );

            for ( int n = 1; n <= 10000000; n++ ) {

                bw.write ( "xxxxx.xx" + FileOps.CRLF); // 10 chars

            }

            bw.close();

            s = fo.readFile ( TEMP_DIR + "file1.txt" );

            Assert.assertTrue ( s == null );

            // gluster read

            s = fo.readFile ( "//ieaavlxglsamn01/glus_n00_appdata/q00/esb/mifid/transaction/processed/2017-10-27/1793783_15216625_3990941_4854-0-New.csv" );

            Assert.assertTrue ( "gluster read", s.length() > 0 );

        } catch ( Exception e ) {

            e.printStackTrace();
            //System.exit ( -1 );

        }

    }

    //    @Test
    public void writeFileTest() {
        try {

            fo.deleteFiles ( TEMP_DIR );

            fo.writeFile ( TEMP_DIR + "writeFileTest.txt", "aaa" + FileOps.CR + "bbb" + FileOps.CR + "ccc" + FileOps.CR);

            String s = fo.readFile ( TEMP_DIR + "writeFileTest.txt" );

            Assert.assertEquals ( "aaa" + FileOps.CR + "bbb" + FileOps.CR + "ccc" + FileOps.CR, s );

        } catch ( Exception e ) {

            e.printStackTrace();
            //System.exit ( -1 );

        }

    }

    //    @Test
    public void countLinesTest() {

        try {

            int n;

            fo.deleteFiles ( TEMP_DIR );
            fo.writeFile ( TEMP_DIR + "file1.txt", "xxx\nxxx\nxxx\n" );
            n = fo.countLines ( TEMP_DIR + "file1.txt" );
            Assert.assertEquals ( 4, n );

            fo.deleteFiles ( TEMP_DIR );
            fo.writeFile ( TEMP_DIR + "file1.txt", "xxx\nxxx\nxxx" );
            n = fo.countLines ( TEMP_DIR + "file1.txt" );
            Assert.assertEquals ( 3, n );

            fo.deleteFiles ( TEMP_DIR );
            fo.writeFile ( TEMP_DIR + "file1.txt", "xxx" );
            n = fo.countLines ( TEMP_DIR + "file1.txt" );
            Assert.assertEquals ( 1, n );

            fo.deleteFiles ( TEMP_DIR );
            fo.writeFile ( TEMP_DIR + "file1.txt", "xxx\n" );
            n = fo.countLines ( TEMP_DIR + "file1.txt" );
            Assert.assertEquals ( 2, n );

            fo.deleteFiles ( TEMP_DIR );
            n = fo.countLines ( TEMP_DIR + "does_not_exist.txt" );
            Assert.assertEquals ( 0, n );

        } catch ( Exception e ) {

            e.printStackTrace();
            //System.exit ( -1 );

        }
    }

    public void filterCsvFile () {
//        try {
//            helper.readCSV(csvFileIn, labelInput, orderedInput, tagOptions, fileExtension);
//            if (StringUtils.isEmpty(cancelParameterTempFile)) {
//                helper.obtainNewParameters(connection, labelInput, orderedInput, tagOptions);
//
//                for (Map.Entry<String, String> entry : labelInput.entrySet()) {
//                    log.debug("Key : " + entry.getKey() + " Value : " + entry.getValue());
//                }
//            }
//            if(StringUtils.isEmpty(cancelParameterTempFile)) {
//                helper.createCSVParametersFile(csvOut, labelInput);
//            } else {
//                csvOut = cancelParameterTempFile;
//            }
//            if(!fileExtension.equalsIgnoreCase(".xml")) {
//                helper.createTemplate(csvFileIn, tempOut, tagOptions,gIsTrsPath);
//            } else {
//                helper.createTemplateXML(csvFileIn, tempOut, tagOptions);
//            }
//
//        } catch (Exception e) {
//            log.catching(e);
//        }
    }

    public void createCSVParametersFile(String csvOut, Map<String, String> labelInput) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        int lineWritten = 0;
        String firstLine = "";

        try {
            String content = "";
            fw = new FileWriter(csvOut);
            bw = new BufferedWriter(fw);
            firstLine = "label Name" + "," + "Value";
            bw.write(firstLine);

            for (Map.Entry<String, String> entry : labelInput.entrySet()) {
                String labelName = entry.getKey();
                String value = entry.getValue();
                bw.write("\r\n");
                content = labelName + "," + value;
                bw.write(content);
                lineWritten++;
            }


        } catch (IOException e) {

        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {

            }
        }
    }
}

