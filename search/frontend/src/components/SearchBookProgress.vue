<template>
  <div>
    <div>
      <h2>searching</h2>
      <div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
    }
  },
  created () {
    this.$store.state.searchBookData = null
    axios.post('/search/book', this.$store.state.searchBookParam).then(({data}) => {
      this.$store.state.searchBookData = data
      this.$router.push('/search/book/result')
    }).catch(() => {
      this.$router.push('/user/login')
    })
  },
  methods: {
  }
}
</script>
