package convertidor

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RegistroConsultaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond RegistroConsulta.list(params), model:[registroConsultaCount: RegistroConsulta.count()]
    }

    def show(RegistroConsulta registroConsulta) {
        respond registroConsulta
    }

    def create() {
        respond new RegistroConsulta(params)
    }

    @Transactional
    def save(RegistroConsulta registroConsulta) {
        if (registroConsulta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (registroConsulta.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond registroConsulta.errors, view:'create'
            return
        }
        
        def tip = convertidor.TipoCambio.list()
            tip = convertidor.TipoCambio.findAllByNombre(registroConsulta.tipoDeCambio)
        
            if(registroConsulta.aTipoDeCambio == "Pesos" ){
            tip = tip.cambioaPesos}
        
            if(registroConsulta.aTipoDeCambio == "DolaresCanada" ){
            tip = tip.cambioaDolaresCan}
        
            if(registroConsulta.aTipoDeCambio == "DolaresEUA" ){
            tip = tip.cambioaDolaresEua}
        
            if(registroConsulta.aTipoDeCambio == "EUR" ){
            tip = tip.cambioaEuros}
        
            if(registroConsulta.aTipoDeCambio == "GBP" ){
            tip = tip.cambioaGBP}
        
            if(registroConsulta.aTipoDeCambio == "Yen" ){
            tip = tip.cambioaYen}
        
        registroConsulta.resultado = registroConsulta.cantidad*tip
        registroConsulta.save flush:true
        

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'registroConsulta.label', default: 'RegistroConsulta'), registroConsulta.id])
                redirect registroConsulta
            }
            '*' { respond registroConsulta, [status: CREATED] }
        }
    }

    def edit(RegistroConsulta registroConsulta) {
        respond registroConsulta
    }

    @Transactional
    def update(RegistroConsulta registroConsulta) {
        if (registroConsulta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (registroConsulta.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond registroConsulta.errors, view:'edit'
            return
        }

        registroConsulta.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'registroConsulta.label', default: 'RegistroConsulta'), registroConsulta.id])
                redirect registroConsulta
            }
            '*'{ respond registroConsulta, [status: OK] }
        }
    }

    @Transactional
    def delete(RegistroConsulta registroConsulta) {

        if (registroConsulta == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        registroConsulta.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'registroConsulta.label', default: 'RegistroConsulta'), registroConsulta.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'registroConsulta.label', default: 'RegistroConsulta'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
