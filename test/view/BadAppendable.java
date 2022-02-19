package view;

import java.io.IOException;

/**
 * This class represents a failing appendable.
 */
public class BadAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("AHH");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("AHH");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("AHH");
  }
}
