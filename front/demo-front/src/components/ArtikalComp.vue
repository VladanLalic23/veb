<template>
    <div class="card">
     
            <div class="card-body">
              <p>{{ artikal.naziv }}</p>
              <p>{{ artikal.cena }} dinara</p>
              <p>{{ artikal.opis }}</p>
              <p>{{ artikal.kolicina }}</p>
              <br />
  
            </div>
  
    </div>
  </template>
  
  <script>
  export default {
    name: "ArtikalComp",
      props: ["artikal"], 
  };
  </script>
  