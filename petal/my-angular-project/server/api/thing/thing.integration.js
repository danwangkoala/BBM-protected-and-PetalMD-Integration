'use strict';

var app = require('../..');

//what is supertest????????????
import request from 'supertest';

var newThing;

describe('Thing API:', function() {

  describe('GET /api/things', function() {
    var things;

    beforeEach(function(done) {
      request(app)
        .get('/api/things')
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          things = res.body;
          done();
        });
    });

    it('should respond with JSON array', function() {
      things.should.be.instanceOf(Array);
    });

  });

  describe('POST /api/things', function(thing) {
    beforeEach(function(done) {
      request(app)
        .post('/api/things')
        .send({
          first_name: thing.first_name,
          last_name: thing.last_name, 
		  license: thing.license,
		  email: thing.email,
		  group_acronym: thing.group_acronym,
		  console_task_display_name: thing.console_task_display_name,
		  group_task_name: thing.group_task_name,
		  group_task_abbreviation: thing.group_task_abbreviation,
		  start_timestamp: thing.start_timestamp,
		  end_timestamp: thing.end_timestamp,
		  contact_kind_1: thing.contact_kind_1,
		  contact_number_1: thing.contact_number_1,
		  contact_kind_2: thing.contact_kind_2, 
		  contact_number_2: thing.contact_number_2,
		  contact_kind_3: thing.contact_kind_3, 
		  contact_number_3: thing.contact_number_3, 
		  contact_kind_4: thing.contact_kind_4, 
		  contact_number_4: thing.contact_number_4
        })
        .expect(201)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          newThing = res.body;
          done();
        });
    });

    it('should respond with the newly created thing', function() {
      newThing.first_name.should.equal(thing.first_name);
      newThing.last_name.should.equal(thing.last_name);
	  newThing.license.should.equal(thing.license);
	  newThing.email.should.equal(thing.email);
	  newThing.group_acronym.should.equal(thing.group_acronym);
	  newThing.console_task_display_name.should.equal(thing.console_task_display_name);
	  newThing.group_task_name.should.equal(thing.group_task_name);
	  newThing.group_task_abbreviation.should.equal(thing.group_task_abbreviation);
	  newThing.start_timestamp.should.equal(thing.start_timestamp);
	  newThing.end_timestamp.should.equal(thing.end_timestamp);
	  newThing.contact_kind_1.should.equal(thing.contact_kind_1);
	  newThing.contact_number_1.should.equal(thing.contact_number_1);
	  newThing.contact_kind_2.should.equal(thing.contact_kind_2);
	  newThing.contact_number_2.should.equal(thing.contact_number_2);
	  newThing.contact_kind_3.should.equal(thing.contact_kind_3);
	  newThing.contact_number_3.should.equal(thing.contact_number_3);
	  newThing.contact_kind_4.should.equal(thing.contact_kind_4);
	  newThing.contact_number_4.should.equal(thing.contact_number_4);
    });

  });

  describe('GET /api/things/:id', function() {
    var thing;

    beforeEach(function(done) {
      request(app)
        .get('/api/things/' + newThing._id)
        .expect(200)
        .expect('Content-Type', /json/)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          thing = res.body;
          done();
        });
    });

    afterEach(function() {
      thing = {};
    });

    it('should respond with the requested thing', function() {
      thing.first_name.should.equal('New Thing');
      thing.last_name.should.equal('This is the brand new thing!!!');
    });

  });

  describe('PUT /api/things/:id', function() {
    var updatedThing;

    beforeEach(function(done) {
      request(app)
        .put('/api/things/' + newThing._id)
        .send({
          first_name: 'Updated Thing',
          last_name: 'This is the updated thing!!!'
        })
        .expect(200)
        .expect('Content-Type', /json/)
        .end(function(err, res) {
          if (err) {
            return done(err);
          }
          updatedThing = res.body;
          done();
        });
    });

    afterEach(function() {
      updatedThing = {};
    });

    it('should respond with the updated thing', function() {
      updatedThing.first_name.should.equal('Updated Thing');
      updatedThing.last_name.should.equal('This is the updated thing!!!');
    });

  });

  describe('DELETE /api/things/:id', function() {

    it('should respond with 204 on successful removal', function(done) {
      request(app)
        .delete('/api/things/' + newThing._id)
        .expect(204)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

    it('should respond with 404 when thing does not exist', function(done) {
      request(app)
        .delete('/api/things/' + newThing._id)
        .expect(404)
        .end((err, res) => {
          if (err) {
            return done(err);
          }
          done();
        });
    });

  });

});
