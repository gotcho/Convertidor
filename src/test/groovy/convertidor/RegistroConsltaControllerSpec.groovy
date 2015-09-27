package convertidor

import grails.test.mixin.*
import spock.lang.*

@TestFor(RegistroConsltaController)
@Mock(RegistroConslta)
class RegistroConsltaControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.registroConsltaList
            model.registroConsltaCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.registroConslta!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def registroConslta = new RegistroConslta()
            registroConslta.validate()
            controller.save(registroConslta)

        then:"The create view is rendered again with the correct model"
            model.registroConslta!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            registroConslta = new RegistroConslta(params)

            controller.save(registroConslta)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/registroConslta/show/1'
            controller.flash.message != null
            RegistroConslta.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def registroConslta = new RegistroConslta(params)
            controller.show(registroConslta)

        then:"A model is populated containing the domain instance"
            model.registroConslta == registroConslta
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def registroConslta = new RegistroConslta(params)
            controller.edit(registroConslta)

        then:"A model is populated containing the domain instance"
            model.registroConslta == registroConslta
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/registroConslta/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def registroConslta = new RegistroConslta()
            registroConslta.validate()
            controller.update(registroConslta)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.registroConslta == registroConslta

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            registroConslta = new RegistroConslta(params).save(flush: true)
            controller.update(registroConslta)

        then:"A redirect is issued to the show action"
            registroConslta != null
            response.redirectedUrl == "/registroConslta/show/$registroConslta.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/registroConslta/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def registroConslta = new RegistroConslta(params).save(flush: true)

        then:"It exists"
            RegistroConslta.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(registroConslta)

        then:"The instance is deleted"
            RegistroConslta.count() == 0
            response.redirectedUrl == '/registroConslta/index'
            flash.message != null
    }
}
