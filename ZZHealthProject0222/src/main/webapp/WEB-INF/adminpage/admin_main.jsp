<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container" id="adminApp">
		<div class="banner-section spad">
	        <div class="container-fluid">
	        	<h2 class="sectiontitle">예약 현황</h2>
	        	<table class="table">
        			<tr>
         				<th>번호</th>
         				<th></th>
         				<th>아이디</th>
         				<th>업체명</th>
         				<th>예약일</th>
         				<th>예약시간</th>
         				<th></th>
        			</tr>
        			<tr v-for="vo in reserve_list">
          				<td>{{vo.rno}}</td>
          				<td>
           					<img :src="vo.gvo.poster" style="width: 30px;height: 30px">
          				</td>
          				<td>{{vo.userId}}</td>
          				<td>{{vo.gvo.title}}</td>
          				<td>{{vo.rdate}}</td>
          				<td>{{vo.rtime}}</td>
          				<td>
            				<span class="btn btn-xs btn-danger" v-if="vo.reserve_ok==0" @click="ok(vo.rno)">승인대기</span>
            				<span class="btn btn-xs btn-default" v-else>승인완료</span>
          				</td>
        			</tr>
      			</table>
	        </div>
	    </div>
	</div>
	<script>
  /*
       data() => useState()
       mounted() => useEffect()
       methods => function 
  */
  let adminApp=Vue.createApp({
	  data(){
		  return {
			  reserve_list:[]
		  }
	  },
	  mounted(){
		  axios.get('../reserve/reserve_admin_vue.do')
		  .then(response=>{
			  console.log(response.data)
			  this.reserve_list=response.data
		  })
	  },
	  methods:{
		  // 승인 버튼 => UPDATE , Mail
		  ok(rno){
			  axios.get('../reserve/reserve_ok_vue.do',{
				  params:{
					  rno:rno
				  }
			  }).then(response=>{
				  if(response.data=='yes')
				  {
					  axios.get('../reserve/reserve_admin_vue.do')
					  .then(response=>{
						  console.log(response.data)
						  this.reserve_list=response.data
					  })
				  }
				  else
				  {
					  alert(response.data)
				  }
			  })
		  }
	  }
  }).mount("#adminApp")
</script>
</body>
</html>