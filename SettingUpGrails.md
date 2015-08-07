#Setting up Grails on developer computer

# How #

  * Checkout from Mercurial: http://code.google.com/p/rfid-server/source/checkout
  * Maven will create the project files for you:
  * "mvn initialize" - Say Yes if asked to upgrade.
  * "hg revert" - to roll back overwritten files by initialize

  * Start grails webserver: mvn grails:run-app

  * ACEGI security will be placed on the resources, and will require basic html style authentication. Read more on http://www.infoq.com/articles/grails-acegi-integration

## More info on Grails ##
http://grails.org/ and http://infoq.com/grails are good resource for more information on Grails.

This 1 hour long screencast gives you all the information for a quick overview and getting started developing Grails applications - http://www.infoq.com/presentations/rudolph-grails-intro