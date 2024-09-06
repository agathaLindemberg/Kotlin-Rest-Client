In the project, the MainActivity class was modified to integrate two REST services and display information on the same application screen. Below is a detailed description of the implemented functionalities:
<br><br>
  IPAPI Service:<br>
            Configuration: Retrofit is configured to communicate with the IPAPI service.<br>
            Request: Makes a call to obtain information about the current IP.<br>
            Response Processing: Displays detailed IP information (such as city, region, country, latitude, longitude, and organization) in a TextView.
<br>         
  ViaCEP Service:<br>
            Configuration: Retrofit is configured to communicate with the ViaCEP service.<br>
            Request: Makes a call to obtain information about a zip code entered by the user.<br>
            Response Processing: Displays the complete address resulting from the search in an EditText.
