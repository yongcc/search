<template>
  <div>
    <div class="bookDetail">
      <h2 v-on:click="goList">[go list]</h2>
      <div>{{book.title}}</div>
      <div><img v-bind:src="book.thumbnail" /></div>
      <div>isbn: {{book.isbn}}</div>
      <div>출판일: {{book.date}}</div>
      <div>저자: {{book.authors}}</div>
      <div>출판사: {{book.publisher}}</div>
      <div>정가: {{book.price}}</div>
      <div>판매가: {{book.salePrice}}</div>
      <div>{{book.contents}}</div>
    </div>
  </div>
</template>

<script>
// import axios from 'axios'

export default {
  data () {
    return {
      book: this.$store.state.searchBookDetail
    }
  },
  created () {
  },
  methods: {
    goList () {
      this.$router.push('/search/book/progress')
    }
  }
}
</script>

<style>
.bookDetail div {
    margin-bottom: 10px;
}
</style>
