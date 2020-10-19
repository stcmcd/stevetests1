package steve;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;
import java.io.FileWriter;

import static java.lang.System.exit;

public class FileOps {
    public static final int            DIR_NOT_FOUND               = -1;
    public static final int            MAX_FILE_LEN                = 10000000;
    public static final boolean        FW_APPEND                   = true;
    public static final boolean        FW_OVERWRITE                = false;
    public static final String         CR                          = "\r";
    public static final String         LF                          = "\n";
    public static final String         CRLF                        = "\r\n";
    public static final String         QQ                          = "\"";

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                                //
    //  getPath                                                                                                       //
    //                                                                                                                //
    //  I   pathIn                                                                                                    //
    //  O   pathOut                                                                                                   //
    //                                                                                                                //
    //  Get absolute path of file or directory.                                                                       //
    //                                                                                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getPath ( String pathIn ) {

        try {

            File f = new File ( pathIn );

            String s = f.getAbsolutePath();

            return s;

        } catch ( Exception e ) {

            e.printStackTrace();
            exit ( -1 );
            return null;

        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                                //
    //  makeDir                                                                                                       //
    //                                                                                                                //
    //  CANNOT create multi levels in one hit                                                                         //
    //                                                                                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void makeDir ( String dirName ) {

        try {

            File f = new File ( dirName );

            f.mkdir();


        } catch ( Exception e ) {

            e.printStackTrace();
            exit ( -1 );

        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                                //
    //  copyFile                                                                                                      //
    //                                                                                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void copyFile ( String source, String target ) {

        String s;

        try {
            s = readFile ( source );

            if ( s == null ) return; // empty source: do nothing

            deleteFile ( target );

            writeFile ( target, s );

        } catch ( Exception e ) {

            s = e.getMessage();

            System.out.println ( "EXCEPTION: " + s );

            exit(1);

        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                                //
    //  readFile                                                                                                      //
    //                                                                                                                //
    //  Fast read.                                                                                                    //
    //                                                                                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String readFile ( String filename ) {

        String s = "";

        try {

            FileReader fr = new FileReader     ( filename );
            BufferedReader br = new BufferedReader ( fr );

            char[] a = new char [ MAX_FILE_LEN ];

            int n = br.read ( a, 0, a.length );

            if ( n == -1 ) {

                // EOF

                return null;
            }

            s =  new String ( a, 0, n );

            if ( s.length() >= MAX_FILE_LEN ) return null;

            br.close();

            // Force DOS format
            s = s.replace ( CRLF, LF   );
            s = s.replace ( LF  , CRLF );

            return s;

        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException " + filename);
        } catch (IOException ex2) {
            System.out.println("IOException " + filename);
        } catch ( Exception e ) {
            System.out.println("Problem with file " + filename);
        }

        return null;

    }

    public String fileRead(String filename) throws IOException {
        BufferedReader br;
        String sCurrentLine;
        StringBuilder result = new StringBuilder();
        br = new BufferedReader(new FileReader(filename));
        while ((sCurrentLine = br.readLine()) != null) {
            result.append(sCurrentLine);
        }

        return result.toString();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                                //
    //  writeFile                                                                                                     //
    //                                                                                                                //
    //  Appends data to specified file.                                                                               //
    //                                                                                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void writeFile ( String filename, String s ) {

        try {

            FileWriter fw = new FileWriter ( filename, true ); // append
            BufferedWriter bw = new BufferedWriter ( fw );

            bw.write ( s );

            bw.close();

        } catch ( Exception e ) {

            e.printStackTrace();
            exit ( -1 );

        }

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                                //
    //  deleteFile                                                                                                    //
    //                                                                                                                //
    //  Can use to delete dirs but must be empty first                                                                //
    //                                                                                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void deleteFile ( String filename ) {

        try {

            File f = new File ( filename );
            f.delete();

        } catch ( Exception e ) {

            e.printStackTrace();
            exit ( -1 );

        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                                //
    //  deleteFiles                                                                                                   //
    //                                                                                                                //
    //  Delete files in given dir.                                                                                    //
    //  Can use to delete dirs but must be empty first                                                                //
    //                                                                                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void deleteFiles ( String dirName ) {

        try {

            File    dir         = new File ( dirName );
            File[]  fileArray   = dir.listFiles();

            if(fileArray.length != 0) {
                for (File f : fileArray) {
                    f.delete();
                }
            }

        } catch ( Exception e ) {
            System.out.println("exception deleting files from out folder " + dirName);

            // dir specified does not exist - do nothing

        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                                //
    //  getFileNames                                                                                                  //
    //                                                                                                                //
    //  Get string array containing all matching names.                                                               //
    //                                                                                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String[] getFileNames ( String dirName, String pattern ) {

        try {

            int             n;
            String          fileName;
            String[]        a;
            File            dir;
            File[]          fileArray;

            LinkedList<String> ll = new LinkedList<String>();

            dir = new File ( dirName );

            fileArray = dir.listFiles();

            for ( File f: fileArray ) {

                fileName = f.getName();

                String s = fileName.replaceAll ( pattern, "" );

                if ( !s.equals(fileName) ) {

                    // <></>his file matches the pattern

                    ll.add ( fileName );

                }

            }

            Collections.sort ( ll );

            a = new String [ ll.size() ];

            n = 0;

            for ( String s: ll ) {

                a[n] = s;

                n++;

            }

            return a;


        } catch ( Exception e ) {

            e.printStackTrace();
            exit ( -1 );

        }

        return null;
    }

    public String[] getFileNames1 ( String dirName, String pattern ) {

        try {

            int             n;
            String          fileName;
            String[]        a;
            File            dir;
            File[]          fileArray;

            List<String> ll = new ArrayList<String>();

            dir = new File ( dirName );

            fileArray = dir.listFiles();

            for ( File f: fileArray ) {

                fileName = f.getName();

                String s = fileName.replaceAll ( pattern, "" );

                if ( !s.equals(fileName) ) {

                    // <></>his file matches the pattern

                    ll.add ( fileName );

                }

            }

            Collections.sort ( ll );

            a = new String [ ll.size() ];

            n = 0;

            for ( String s: ll ) {

                a[n] = s;

                n++;

            }

            return a;


        } catch ( Exception e ) {

            e.printStackTrace();
            exit ( -1 );

        }

        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                                //
    //  countLines                                                                                                    //
    //                                                                                                                //
    //  Get line count for given file.                                                                                //
    //                                                                                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int countLines ( String filename ) {

        String s;
        String[] a;
        int n;

        s = readFile ( filename );

        if ( s == null ) return 0;

        a = s.split ( CR );

        n = a.length;

        return n;

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                                //
    //  countFiles                                                                                                    //
    //                                                                                                                //
    //  Count number of files in given dir.                                                                           //
    //                                                                                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int countFiles ( String dirName ) {

        int n = 0;
        String s;

        try {

            File    dir         = new File ( dirName );
            File[]  fileArray   = dir.listFiles();

            n = fileArray.length;

        } catch ( Exception e ) {

            // return -1 if dir not found
            n = -1;

        }

        return n;

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                                                                                                //
    //  countMatches                                                                                                  //
    //                                                                                                                //
    //     I    targetString                                                                                          //
    //     I    subString                                                                                             //
    //                                                                                                                //
    //  Count number sub strings occurring in target string.                                                          //
    //                                                                                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int countMatches ( String targetString, String subString ) {

        int lastIndex = 0;
        int count = 0;

        if ( targetString == null ) return 0;

        while(lastIndex != -1){

            lastIndex = targetString.indexOf ( subString, lastIndex );

            if ( lastIndex != -1 ){

                count ++;

                lastIndex = lastIndex + subString.length();

            }
        }

        return count;

    }
    public File copyFileFromSourceToDestination(File sourceFile, String destinationLocation) throws IOException {
//        String destinationLocation = getEvidenceFolder();
        String sourceFileName = sourceFile.getName();
        File destinationFile = new File(destinationLocation);

        try {
            FileUtils.copyFileToDirectory(sourceFile,destinationFile);
            destinationFile = new File(destinationLocation.concat("/").concat(sourceFileName));
        } catch (IOException e) {
            throw new IOException("Unable to find destination file "+sourceFile,e);
        }

        return destinationFile;
    }

    public void copyFileFromSourceToDestinationRenaming(File sourceFile, String destinationLocation, String filename) throws IOException {

        try {
            String sourceFileName = sourceFile.getAbsolutePath();

            File fileToBeRenamedFrom = new File(sourceFileName);
            File fileToBeRenamedTo;


            if (System.getProperty("env_name").contains("jenkins")) {
                fileToBeRenamedTo = new File(destinationLocation + '/' + "tmp" + filename);
            } else {
                fileToBeRenamedTo = new File(destinationLocation + '\\' + "tmp" + filename);
            }

            fileToBeRenamedTo.createNewFile();

            try {
                FileUtils.copyFile(fileToBeRenamedFrom, fileToBeRenamedTo);
                Thread.sleep(3000); //Seems to be a delay copying the file

                if (System.getProperty("env_name").contains("jenkins")) {
                    fileToBeRenamedTo.renameTo(new File(destinationLocation + '/' + filename));
                } else {
                    fileToBeRenamedTo.renameTo(new File(destinationLocation + '\\' + filename));
                }
            } catch (IOException e) {
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
