<template>
  <div>
    <div>
      <h2 v-on:click="goMain">[go main]</h2>
      <div>{{message}}</div>
      <div>
        <div class="bookDiv" v-for="(book, i) in books" :key="book.title" v-on:click="goDetail(i)">
          {{book.title}} - {{book.authors}} - {{book.price}} - {{book.date}}
        </div>
      </div>
      <div class="pageDiv">
        <span v-for="page in pages" :key="page.page">
          <span v-on:click="goPage(page.page)">
            {{page.text}}
          </span>
        </span>
        <span> ... </span>
        <span>{{maxPage}}</span>
      </div>
    </div>
  </div>
</template>

<script>
// import axios from 'axios'

export default {
  data () {
    return {
      books: '',
      pages: '',
      maxPage: '',
      message: ''
    }
  },
  created () {
    this.books = this.$store.state.searchBookData.searchBooks
    if (!this.books) {
      this.message = 'empty'
    }
    var param = this.$store.state.searchBookParam
    var currPage = param.page || 1
    var minPage = Math.max(currPage - 9, 1)
    var maxPage = Math.min(currPage + 9, this.$store.state.searchBookData.maxPage)
    var pages = []
    for (var i = minPage; i <= maxPage; i++) {
      var pageInfo = {page: i, text: (i === currPage ? '[' + i + ']' : i)}
      pages.push(pageInfo)
    }
    this.pages = pages
    this.maxPage = this.$store.state.searchBookData.maxPage
  },
  methods: {
    goPage (page) {
      this.$store.state.searchBookParam.page = page
      this.$router.push('/search/book/progress')
    },
    goDetail (index) {
      this.$store.state.searchBookDetail = this.$store.state.searchBookData.searchBooks[index]
      this.$router.push('/search/book/detail')
    },
    goMain () {
      this.$router.push('/search/book')
    }
  }
}
</script>

<style>
.bookDiv {
    margin-bottom: 20px;
}
.pageDiv {
    font-size: 20px;
    margin-top: 25px;
}
</style>
