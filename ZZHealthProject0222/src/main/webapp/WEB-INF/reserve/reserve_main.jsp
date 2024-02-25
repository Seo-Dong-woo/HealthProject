<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style type="text/css">
#reserveApp {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
 .rounded {
      -moz-border-radius:20px 20px 20px 20px; 
      border-radius:20px 20px 20px 20px;
      border:solid 1px #ffffff;
      background-color:#2b6bd1;
      padding:10px;
      color:#ffffff;
    }
  td.link:hover,tr.tr_link:hover{
    cursor: pointer;
  }
</style>
</head>
<body>
	<div class="container" id="reserveApp">
		<div class="banner-section spad">
	        <div class="container-fluid">
	        	<h2 class="sectiontitle">헬스장 예약</h2>
	        	<table class="table">
     				<tr>
       					<td class="text-center" rowspan="4" width=40%>
         					<table class="table">
           						<h3 class="text-center">헬스장 정보</h3>
           						<tr>
             						<td class="text-center inline">
               							<input type="button" class="btn-xs btn-success" value="도봉구" @click="gymSelect('도봉구')">&nbsp;
               							<input type="button" class="btn-xs btn-info" value="중랑구" @click="gymSelect('중랑구')">&nbsp;
               							<input type="button" class="btn-xs btn-warning" value="강북구" @click="gymSelect('강북구')">&nbsp;
               							<input type="button" class="btn-xs btn-danger" value="강서구" @click="gymSelect('강서구')">&nbsp;
               							<input type="button" class="btn-xs btn-success" value="노원구" @click="gymSelect('노원구')">&nbsp;
               							<input type="button" class="btn-xs btn-info" value="영등포구" @click="gymSelect('영등포구')">&nbsp;
               							<input type="button" class="btn-xs btn-warning" value="금천구" @click="gymSelect('금천구')">&nbsp;
               							<input type="button" class="btn-xs btn-danger" value="마포구" @click="gymSelect('마포구')">&nbsp;
               							<input type="button" class="btn-xs btn-success" value="서초구" @click="gymSelect('서초구')">&nbsp;
               							<input type="button" class="btn-xs btn-info" value="은평구" @click="gymSelect('은평구')">&nbsp;
               							<input type="button" class="btn-xs btn-warning" value="동대문구" @click="gymSelect('동대문구')">&nbsp;
               							<input type="button" class="btn-xs btn-danger" value="강동구" @click="gymSelect('강동구')">&nbsp;
               							<input type="button" class="btn-xs btn-success" value="광진구" @click="gymSelect('광진구')">&nbsp;
               							<input type="button" class="btn-xs btn-info" value="구로구" @click="gymSelect('구로구')">&nbsp;
               							<input type="button" class="btn-xs btn-warning" value="중구" @click="gymSelect('중구')">&nbsp;
               							<input type="button" class="btn-xs btn-danger" value="강남구" @click="gymSelect('강남구')">&nbsp;
               							<input type="button" class="btn-xs btn-success" value="관악구" @click="gymSelect('관악구')">&nbsp;
               							<input type="button" class="btn-xs btn-info" value="동작구" @click="gymSelect('동작구')">&nbsp;
               							<input type="button" class="btn-xs btn-warning" value="성동구" @click="gymSelect('성동구')">&nbsp;
               							<input type="button" class="btn-xs btn-danger" value="성북구" @click="gymSelect('성북구')">&nbsp;
               							<input type="button" class="btn-xs btn-success" value="용산구" @click="gymSelect('용산구')">&nbsp;
               							<input type="button" class="btn-xs btn-info" value="양천구" @click="gymSelect('양천구')">
             						</td>
           						</tr>
         					</table>
         					<div style="overflow-y:auto;height:700px">
          						<table class="table">
            						<tr v-for="vo in gym_list" class="tr_link" @click="gymNumber(vo.gno)">
              							<td><img :src="vo.poster" style="width: 30px;height: 30px"></td>
              							<td class="text-left">{{vo.title}}</td>
            						</tr>
          						</table>
         					</div>
       					</td>
       					<td class="text-center" width="60%">
         					<table class="table">
           						<h3 class="text-center">예약일 정보</h3>
           						<tr>
             						<td>
                						<div class="calendar">
				      						<h2>
				        						<a href="#" v-on:click="onClickPrev(currentMonth)">◀</a>
				        							{{currentYear}}년 {{currentMonth}}월
				        						<a href="#" v-on:click="onClickNext(currentMonth)">▶</a>
				      						</h2>
				     						<table class="table table-hover">
				          						<thead>
				            						<tr>
				              							<td v-for="(weekName, index) in weekNames" v-bind:key="index">
				                							{{weekName}}
				              							</td>
				            						</tr>
				          						</thead>
				          						<tbody>
				            						<tr v-for="(row, index) in currentCalendarMatrix" :key="index">
				              							<td v-for="(day, index2) in row" :key="index2" style="padding:20px;" :class="day>=realDay?'link':''">
				                							<span v-if="day>=realDay" @click="change(day)" style="color:black">
					                							<span v-if="day===currentDay" class="rounded">
					                  								{{day}}
					                							</span>
					                							<span v-else>
					                  								{{day}}
					                							</span>
				                							</span>
				                							<span v-else style="color:gray">
				                   								{{day}}
				                							</span>
				              							</td>
				            						</tr>
				          						</tbody>
				      						</table>    
				  						</div>
             						</td>
           						</tr>
         					</table>
       					</td>
     				</tr>
     				<tr>
      					<td class="text-center" width=60%>
        					<table class="table">
          						<h3 class="text-center">시간 정보</h3>
          						<tr v-show="tShow">
            						<td class="text-center">
              							<span class="btn btn-xs btn-success" v-for="time in time_list" style="margin-left: 2px" @click="timeSelect(time)">{{time}}</span>
            						</td>
          						</tr>
        					</table>
      					</td>
     				</tr>
     				<tr v-show="rShow">
       					<td class="text-center" width=60%>
        					<input type=button class="btn-lg btn-primary" value="예약하기" @click="reserveOk()">
       					</td>
     				</tr>
   				</table>
	        </div>
		</div>
	</div>
	<script>
	let rApp=Vue.createApp({
		data(){
			return {
				weekNames: ["월요일", "화요일", "수요일","목요일", "금요일", "토요일", "일요일"],
			      rootYear: 1904,
			      rootDayOfWeekIndex: 4, // 2000년 1월 1일은 토요일
			      currentYear: new Date().getFullYear(),
			      currentMonth: new Date().getMonth()+1,
			      currentDay: new Date().getDate(),
			      currentMonthStartWeekIndex: null,
			      currentCalendarMatrix: [],
			      endOfDay: null,
			      memoDatas: [],
			      realDay:new Date().getDate(),
			      address:'도봉구',
			      gym_list:[],
			      gno:0,
			      time_list:['10:00', '11:00', '12:00','13:00','14:00','17:00','18:00','19:00','20:00','21:00'],
			      tShow:false,
			      time:'',
			      iShow:false,
			      rShow:false
			}
		},
		mounted(){
			this.init()
			this.dataSend()
		},
		methods:{
			   reserveOk(){
				 axios.post('../reserve/reserve_ok.do',null,{
					 params:{
						 gno:this.gno,
						 rDate:this.currentYear+"년도 "+this.currentMonth+"월 "+this.currentDay + "일",
						 rTime:this.time
					 }
				 }).then(response=>{
					 if(response.data==='yes')
				     {
						 location.href="../mypage/mypage.do" 
				     }
					 else
				     {
						 alert("헬스장 예약에 실패하셨습니다")
				     }
				 })  
			   },
			   timeSelect(time){
				 this.time=time;
				 this.rShow=true
			   },
			   gymNumber(gno){
				 this.gno=gno;  
			   },
			  init(){
		        this.currentMonthStartWeekIndex = this.getStartWeek(this.currentYear, this.currentMonth);
		        this.endOfDay = this.getEndOfDay(this.currentYear, this.currentMonth);
		        this.initCalendar();
		      },
		      initCalendar(){
		        this.currentCalendarMatrix = [];
		        let day=1;
		        for(let i=0; i<6; i++){
		          let calendarRow = [];
		          for(let j=0; j<7; j++){
		            if(i==0 && j<this.currentMonthStartWeekIndex){
		              calendarRow.push("");
		            }
		            else if(day<=this.endOfDay){
		              calendarRow.push(day);
		              day++;
		            }
		            else{
		              calendarRow.push("");
		            }
		          }
		          this.currentCalendarMatrix.push(calendarRow);
		        }
		      },
		      getEndOfDay(year, month){
		          switch(month){
		              case 1:
		              case 3:
		              case 5:
		              case 7:
		              case 8:
		              case 10:
		              case 12:
		                return 31;
		                break;
		              case 4:
		              case 6:
		              case 9:
		              case 11:
		                return 30;
		                break;
		              case 2:
		                if( (year%4 == 0) && (year%100 != 0) || (year%400 == 0) ){
		                return 29;   
		                }
		                else{
		                    return 28;
		                }
		                break;
		              default:
		                console.log("unknown month " + month);
		                return 0;
		                break;
		          }
		      },
		      getStartWeek(targetYear, targetMonth){
		        let year = this.rootYear;
		        let month = 1;
		        let sumOfDay = this.rootDayOfWeekIndex;
		        while(true){
		          if(targetYear > year){
		            for(let i=0; i<12; i++){
		              sumOfDay += this.getEndOfDay(year, month+i);
		            }
		            year++;
		          }
		          else if(targetYear == year){
		            if(targetMonth > month){
		              sumOfDay += this.getEndOfDay(year, month);
		              month++;
		            }
		            else if(targetMonth == month){
		              return (sumOfDay)%7;
		            }
		          }
		        }
		      },
		      onClickPrev(month){
		        month--;
		        if(month<=0){
		          this.currentMonth = 12;
		          this.currentYear -= 1;
		        }
		        else{
		          this.currentMonth -= 1;
		        }
		        this.init();
		      },
		      onClickNext(month){
		        month++;
		        if(month>12){
		          this.currentMonth = 1;
		          this.currentYear += 1;
		        }
		        else{
		          this.currentMonth += 1;
		        }
		        this.init();
		      },
		      isToday: function(year, month, day){
		        let date = new Date();
		        return year == date.getFullYear() && month == date.getMonth()+1 && day == day; 
		      },
		      change(day){
		    	 this.currentDay=day;
		    	 this.tShow=true
		    	 //this.isToday(this.currentYear,.this.currentMonth,this.currentDay)
		      },
		      // 맛집
		      dataSend(){
		    	axios.get('../reserve/gym_list_vue.do',{
		    		params:{
		    			address:this.address
		    		}
		    	}).then(response=>{
		    		console.log(response.data)
		    		this.gym_list=response.data
		    	})  
		      },
		      gymSelect(address){
		    	  this.address=address;
		    	  this.dataSend()
		      }
		      // 시간 
		      // 인원
		      // 데이터 전송(예약처리) => 화면 변경 (마이페이지로 이동)
		  }
	}).mount("#reserveApp")
	</script>
</body>
</html>