In the project, the MainActivity class was modified to integrate two REST services and display information on the same application screen. Below is a detailed description of the implemented functionalities:
  IPAPI Service:
            Configuration: Retrofit is configured to communicate with the IPAPI service.
            Request: Makes a call to obtain information about the current IP.
            Response Processing: Displays detailed IP information (such as city, region, country, latitude, longitude, and organization) in a TextView.
  ViaCEP Service:
            Configuration: Retrofit is configured to communicate with the ViaCEP service.
            Request: Makes a call to obtain information about a zip code entered by the user.
            Response Processing: Displays the complete address resulting from the search in an EditText.
