var loginId = "";
var password = "*************";
var partnerId = 0;
var expiry = 86400;
var privileges = "*";
var otp = "";

client.user.loginByLoginId(function(results) {
  if (results && results.code && results.message) {
    console.log('Kaltura Error', results);
  } else {
    console.log('Kaltura Result', results);
  }
},
loginId,
password,
partnerId,
expiry,
privileges,
otp);