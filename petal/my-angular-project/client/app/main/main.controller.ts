'use strict';

(function() {

class MainController {

  constructor($http) {
    this.$http = $http;
    this.awesomeThings = [];

    $http.get('/api/things').then(response => {
      this.awesomeThings = response.data;
    });
  }

  addThing(newThing) {
    if (newThing) {
	  
      this.$http.post('/api/things', newThing);
      
    }
  }

  deleteThing(thing) {
    this.$http.delete('/api/things/' + thing._id);
  }
}

angular.module('myAngularProjectApp')
  .controller('MainController', MainController);

})();
