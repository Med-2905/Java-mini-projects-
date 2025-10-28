package food;

public class StockInsuffisantException extends KendiFoodException {
    public StockInsuffisantException(String articleId, int stockDisponible) {
        super("Stock insuffisant pour l'article " + articleId + " (disponible: " + stockDisponible + ")");
    }
}