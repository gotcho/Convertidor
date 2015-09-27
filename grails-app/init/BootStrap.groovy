class BootStrap  {

  def init = { servletContext ->
  def pesos = new convertidor.TipoCambio(nombre:"Pesos",
      cambioaPesos:1.0,
      cambioaDolaresEua:0.059,
      cambioaDolaresCan:0.079,
      cambioaEuros:0.053,
      cambioaGBP:0.039,
      cambioaYen:7.11)
    pesos.save()
    if(pesos.hasErrors()){ println pesos.errors
        }
    
    def dolcan = new convertidor.TipoCambio(nombre:"DolaresCanada",
      cambioaPesos:12.73,
      cambioaDolaresEua:0.75,
      cambioaDolaresCan:1.0,
      cambioaEuros:0.67,
      cambioaGBP:0.49,
      cambioaYen:90.44)
    dolcan.save()
    if(dolcan.hasErrors()){ println dolcan.errors}
    
    
    def doleua = new convertidor.TipoCambio(nombre:"DolaresEUA",
      cambioaPesos:16.97,
      cambioaDolaresEua:1.0,
      cambioaDolaresCan:1.336,
      cambioaEuros:0.5433,
      cambioaGBP:0.6591,
      cambioaYen:10)
    doleua.save()
    if(doleua.hasErrors()){ println doleua.errors}
    
    
    def euro = new convertidor.TipoCambio(nombre:"EUR",
      cambioaPesos:0.043,
      cambioaDolaresEua:1.12,
      cambioaDolaresCan:1.49,
      cambioaEuros:1.0,
      cambioaGBP:0.74,
      cambioaYen:135.17)
    euro.save()
    if(euro.hasErrors()){ println euro.errors}
    
        
    def brit = new convertidor.TipoCambio(nombre:"GBP",
      cambioaPesos:25.078,
      cambioaDolaresEua:1.52,
      cambioaDolaresCan:2.02,
      cambioaEuros:1.36,
      cambioaGBP:1,
      cambioaYen:183.25)
    brit.save()
    if(brit.hasErrors()){ println brit.errors}
    
        
    def jap = new convertidor.TipoCambio(nombre:"Yen",
      cambioaPesos:0.14,
      cambioaDolaresEua:0.008,
      cambioaDolaresCan:0.011,
      cambioaEuros:0.0074,
      cambioaGBP:0.005,
      cambioaYen:1)
    jap.save()
    if(jap.hasErrors()){ println jap.errors}
    
     
    }
    def destroy = {}
}
