# UHF Cordova Plugin

超高频插件

## Install

- 下载到本地安装：

  ```shell
  cordova plugin add https://github.com/nianliang/UHF.git
  ```

## Usage

### API
- [Android]
cordova.plugins.uhf.getEpcList(function(result){
                            console.log('success:',result);
                         },function(error){
                            console.log('fail:',error);
                        })
- [Android]
cordova.plugins.uhf.setOutputPower("26",function(result){
                                        console.log('success:',(result?result:''));
                                    },function(error){
                                        console.log('fail:',error);
                                    })




