package convertidor

class TipoCambio {
    
    String nombre
    BigDecimal  cambioaPesos
    BigDecimal  cambioaDolaresEua
    BigDecimal  cambioaDolaresCan
    BigDecimal  cambioaEuros
    BigDecimal  cambioaGBP
    BigDecimal  cambioaYen
    Date dateCreated
    
    static constraints = {
        nombre (inList:["Pesos", "DolaresCanada", "DolaresEUA", "EUR", "GBP", "Yen"], unique: true)
        cambioaPesos (scale: 4)
        cambioaDolaresEua (scale: 4)
        cambioaDolaresCan (scale: 4)
        cambioaEuros (scale: 4)
        cambioaGBP (scale: 4)
        cambioaYen (scale: 4)
        dateCreated (scale: 4)
    }
}
