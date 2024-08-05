package components;

/**
 * Armazena o valor de linha e coluna de uma posicao
 * @author filip
 */
public class Posicao {
    private int lin;
    private int col;
    
    /**
     * Atribui o valor da posicao (i, j)
     * @param i linha
     * @param j coluna
     */
    public void set(int i, int j)
    {
        lin = i;
        col = j;
    }
    
    /**
     * Atribui o valor da linha i
     * @param i linha
     */
    public void setLin(int i)
    {
        lin = i;
    }
    
    /**
     * Atribui o valor da linha j
     * @param j coluna 
     */
    public void setCol(int j)
    {
        col = j;
    }
    
    /**
     *
     * @return numero da linha
     */
    public int getLin()
    {
        return lin;
    }
    
    /**
     * 
     * @return numero da coluna 
     */
    public int getCol()
    {
        return col;
    }
}
