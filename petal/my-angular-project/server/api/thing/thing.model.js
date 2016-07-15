'use strict';

var mongoose = require('bluebird').promisifyAll(require('mongoose'));
//define the structure of things in mongoDB	
var ThingSchema = new mongoose.Schema({
  first_name: String,
  last_name: String,
  license: String,
  email: String, 
  group_acronym: String,
  console_task_display_name: String,
  group_task_name: String,
  group_task_abbreviation: String,
  start_timestamp: String,
  end_timestamp: String,
  contact_kind_1: String,
  contact_number_1: String, 
  contact_kind_2: String, 
  contact_number_2: String, 
  contact_kind_3: String, 
  contact_number_3: String, 
  contact_kind_4: String, 
  contact_number_4: String
});

export default mongoose.model('Thing', ThingSchema);


//----------original--------------------//
/*
'use strict';

var mongoose = require('bluebird').promisifyAll(require('mongoose'));
//define the structure of things in mongoDB	
var ThingSchema = new mongoose.Schema({
  name: String,
  info: String,
  active: Boolean
});

export default mongoose.model('Thing', ThingSchema);
*/
