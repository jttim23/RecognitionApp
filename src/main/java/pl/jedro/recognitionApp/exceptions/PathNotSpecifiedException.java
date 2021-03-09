package pl.jedro.recognitionApp.exceptions;

import java.io.FileNotFoundException;

public class PathNotSpecifiedException extends FileNotFoundException {


    public PathNotSpecifiedException() {
    }

    public PathNotSpecifiedException(String s) {
        super(s);
    }
}
