<template>
  <div class="nav">
    <button class="home-btn" v-on:click="izlogujSe()">Izloguj se</button>
  </div>
  <div class="container">
    <h1>Dobrodosli - kupac</h1>
    <a type="button" v-bind:href="href" class="home-btn">Pregled profila</a
    ><br />
    <a type="button" v-bind:href="hrefPorudzbine" class="home-btn"
      >Pregled porudzbina</a
    ><br />
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "KupacView",
  computed: {
    href() {
      return "/kupac/profile/" + this.$route.params.id;
    },
    hrefPorudzbine() {
      return "/kupac/porudzbine/" + this.$route.params.id;
    },
  },
  methods: {
    izlogujSe: function () {
      axios
        .get("http://localhost:8080/api/logout", { withCredentials: true })
        .then((res) => {
          this.$router.push("/login");
        })
        .catch((err) => {
          alert(err.response.data);
          this.$router.push("/login");
        });
    },
  },
};
</script>
