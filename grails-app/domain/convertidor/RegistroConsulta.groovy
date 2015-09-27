package convertidor

class RegistroConsulta {
    
    String aTipoDeCambio
    String tipoDeCambio
    BigDecimal  cantidad
    BigDecimal  resultado = 0
    Date dateCreated //Note: this is a special name
    

     static constraints = {
        tipoDeCambio inList:["Pesos", "DolaresCanada", "DolaresEUA", "EUR", "GBP", "Yen"]
        aTipoDeCambio inList:["Pesos", "DolaresCanada", "DolaresEUA", "EUR", "GBP", "Yen"]
        cantidad (scale: 4)
        resultado (scale: 4)
    }
}
