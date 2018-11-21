# 关于动态权限的动态申请介绍和管理
## 动态权限由于在6.0以后有很多不在mainfest中注册，需要动态申请，这里的动态权限主要用到第三方库<br>

## 具体使用步骤是：
### 1.添加依赖；<br>
2.动态申请权限；<br>
3.动态申请权限之后进行回调；<br>



(1)compile 'com.lovedise:permissiongen:0.0.6'<br>

(2)
```
private void :permissiongen() {
//处理需要动态申请的权限
PermissionGen.with(LocationActivity.this)
.addRequestCode(SUCCESSCODE)
.permissions(
Manifest.permission.ACCESS_COARSE_LOCATION,
Manifest.permission.ACCESS_FINE_LOCATION,
Manifest.permission.WRITE_EXTERNAL_STORAGE,
Manifest.permission.READ_EXTERNAL_STORAGE,
Manifest.permission.READ_PHONE_STATE
).request();
}
```
(3)//申请权限结果的返回
```
@Override
public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
}
```
//权限申请成功
```
@PermissionSuccess(requestCode = Constant.SUCCESSCODE)
public void doSomething() {
//在这个方法中做一些权限申请成功的事情
}
//申请失败
@PermissionFail(requestCode = Constant.SUCCESSCODE)
public void doFailSomething() {
}
```




