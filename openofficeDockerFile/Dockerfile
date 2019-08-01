#基础镜像为centos
 
FROM centos
 
#拷贝字体
COPY fonts.tar.gz /tmp
#拷贝安装包
COPY Apache_OpenOffice_4.1.6_Linux_x86-64_install-rpm_zh-CN.tar.gz /tmp

RUN cd /tmp && \
yum install -y fontconfig && \
ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
echo "Asia/Shanghai" > /etc/timezone && \
tar -zxvf fonts.tar.gz  && \
cp /tmp/fonts/* /usr/share/fonts && \
#tar -zxvf fonts.tar.gz  && \
#cd fonts/ && \
#mkfontscale && \
#mkfontdir && \
#fc-cache -fv && \
#source /etc/profile && \
fc-list && \
 
#安装wget
 
#yum -y install wget && \
yum install -y epel-release && yum install -y supervisor && \ 
#解压缩
 
tar -xvf Apache_OpenOffice*.tar.gz && \
 
#安装OpenOffice
 
yum install -y zh-CN/RPMS/*.rpm && \
 
#安装JDK
 
yum install -y java-1.8.0-openjdk.x86_64 && \

#yum install -y mkfontscale && \
#yum install -y mkfontdir && \
#yum install -y apk && \
yum install -y fontconfig \
&& ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo "Asia/Shanghai" > /etc/timezone \
tar -zxvf fonts.tar.gz  && \
cp /tmp/fonts/* /usr/share/fonts && \
#mkfontscale && \
#mkfontdir && \
#fc-cache -fv && \
#source /etc/profile && \
fc-list && \

cd /tmp && \ 
#清除yum缓存
 
yum clean all && \
 
#删除压缩包
 
rm -f Apache_OpenOffice_4.1.6_Linux_x86-64_install-rpm_zh-CN.tar.gz && \
 
#删除解压缩的文件
 
rm -Rf zh-CN && \
 
rm -Rf fonts
#暴露接口
EXPOSE 8100
 
#启动服务，占用8100端口
CMD /opt/openoffice4/program/soffice -headless -nofirststartwizard  -accept="socket,host=0.0.0.0,port=8100;urp;"
