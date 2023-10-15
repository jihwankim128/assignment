function root = NR(func,xi,error,maxit)


i = 1;
xrold = 0;
xr = xi;

fprintf("i root error\n")

while(1)
    xrold = xr;
    xr = xr - subs(func,xr)/subs(diff(func),xr);
    er = (xr-xrold)/xr;

    fprintf("%d %e %f \n",i,double(xr),double(er))
    i = i + 1;

if i >= maxit || abs(er) <= error, break, end
end

clear all
clc

x = 0.8;
for n = 1:10
  a = 2*cos(x)-x^2;
  b = 2*-sin(x)-2*x;
  disp(x);
  x=x-a/b;
  disp(x);
  fprintf("\n");
end
