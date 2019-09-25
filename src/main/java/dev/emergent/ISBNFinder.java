package dev.emergent;

public class ISBNFinder {

    private BookInfoProvider isbnService = null;

    public ISBNFinder() {
        this(ISBNService.getInstance());
    }

    public ISBNFinder(BookInfoProvider bookInfoProvider) {
        isbnService = bookInfoProvider;
    }

    public BookInfo lookup(String ISBN) {

        if (ISBN.length() == 13) {
            return isbnService.retrieve(ISBN);
        }

        if (ISBN.length() < 10) {
            return new BookInfo("ISBN must be 10 characters in length");
        } else if (ISBN.length() > 10) {
            return new BookInfo("ISBN must be 10 characters in length");
        } else {

            BookInfo bookInfo = isbnService.retrieve(ISBN);

            if (null == bookInfo) {
                return new BookInfo("Title not found");
            }

            return bookInfo;
      }
    }

    public String CleanISBN(String ISBN) {
        if (ISBN == null || ISBN.length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ISBN.length(); i++) {
            char c = ISBN.charAt(i);
            if (Character.isDigit(c) || (i == ISBN.length() - 1 && c == 'X')) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public boolean isValid(String ISBN){
        int count = 0;
        for (int i = 0; i < ISBN.length(); i++){
            char c = ISBN.charAt(i);
            if(Character.isDigit(c) || c == ' ' || c == '-' ){
                if (Character.isDigit(c)) {
                    count++;
                }

            } else {
                return false;
            }
        }
        return ((count == 10) || (count == 13));

    }
}
