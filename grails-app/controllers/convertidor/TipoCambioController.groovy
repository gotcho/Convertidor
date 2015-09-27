package convertidor

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TipoCambioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TipoCambio.list(params), model:[tipoCambioCount: TipoCambio.count()]
    }

    def show(TipoCambio tipoCambio) {
        respond tipoCambio
    }

    def create() {
        respond new TipoCambio(params)
    }

    @Transactional
    def save(TipoCambio tipoCambio) {
        if (tipoCambio == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tipoCambio.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tipoCambio.errors, view:'create'
            return
        }

        tipoCambio.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tipoCambio.label', default: 'TipoCambio'), tipoCambio.id])
                redirect tipoCambio
            }
            '*' { respond tipoCambio, [status: CREATED] }
        }
    }

    def edit(TipoCambio tipoCambio) {
        respond tipoCambio
    }

    @Transactional
    def update(TipoCambio tipoCambio) {
        if (tipoCambio == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (tipoCambio.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond tipoCambio.errors, view:'edit'
            return
        }
        
        tipoCambio.nombre = 'perro'
        //tipoCambio.save flush:false
        
        

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoCambio.label', default: 'TipoCambio'), tipoCambio.id])
                redirect tipoCambio
            }
            '*'{ respond tipoCambio, [status: OK] }
        }
    }

    @Transactional
    def delete(TipoCambio tipoCambio) {

        if (tipoCambio == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        tipoCambio.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoCambio.label', default: 'TipoCambio'), tipoCambio.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoCambio.label', default: 'TipoCambio'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
