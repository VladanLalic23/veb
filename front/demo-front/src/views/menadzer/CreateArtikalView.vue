<template>
    <div class="container">
      <div class="form">
        <h1>Kreiranje artikal ne sljaka</h1>
        <label for="naziv">Naziv:</label>
        <input v-model="artikal.naziv" /><br />
        <label for="opis">Opis artikala:</label>
        <input v-model="artikal.opis" /><br />
        <label for="cena">Cena:</label>
        <input v-model="artikal.cena" /><br />
        <label for="kolicina">kolicina:</label>
        <input v-model="artikal.kolicina" /><br />
        <label for="tip">Vrsta:</label>
        <select v-model="artikal.tip">
        <option value="JELO">Jelo</option>
        <option value="PICE">Pice</option>
      </select>
      <br />
        <br />
        <button v-on:click="submit()">Nastavi</button>
      </div>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  import("../../assets/styles/style.css");
  export default {
    name: "CreateArtikalView",
    data: function () {
      return {
       artikal : {
          naziv: "",
          opis: "",
          cena: "",
          kolicina :"",
          tip:"",
          slika:"",
          restoranId:1,
        },
      };
    },
    methods: {
      submit: function () {
        axios
          .post("http://localhost:8080/api/artikal/create", this.artikal, {
            withCredentials: true,
          })
          .then((res) => {
            alert(res.data);
          //  this.$router.push('/menadzer-view/' + this.$route.params.id);
            this.$router.go(-1);
        })
          .catch((err) => {
            alert(err.response.data);
            if (err.response.data === "Morate biti ulogovani") {
              this.$router.push("/login");
            }
          });
      },
    },
  };
  </script>
  
  