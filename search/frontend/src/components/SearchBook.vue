<template>
  <div>
    <h2>search book</h2>
    <form @submit.prevent="onSubmit(query)">
      <input type="text" v-model="query" placeholder="query">
      <input type="submit" value="search">
    </form>
    <div>
      <router-view/>
    </div>
  </div>
</template>

<script>
// import axios from 'axios'

export default {
  data () {
    return {
      query: '',
      result: ''
    }
  },
  methods: {
    onSubmit (query) {
      this.$store.state.searchBookParam = {query}
      this.$router.push('/search/book/progress')
    }
  }
}
</script>
