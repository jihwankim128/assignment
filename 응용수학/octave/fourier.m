clear
pkg load symbolic
syms t;
syms pi;
syms n;
function a_0 = fourier_a0(k1, k2, t, a, b, const)
  a_0=(int(k1,t,[a,0])+int(k2,t,[0,b]));
  if a_0~=0
    if const==1
      a_0=a_0/2*pi;
    else
      a_0=a_0/2;
    endif
  endif
endfunction
function a_n = fourier_an(k1, k2, t, a, b, n, const)
  if const==1
    a_n=int(k1*cos(n*t),t,[a,0])+int(k2*cos(n*t),t,[0,b]);
  else
    a_n=int(k1*cos(n*pi*t),t,[a,0])+int(k2*cos(n*pi*t),t,[0,b]);
  endif
  if a_n~=0
    if const==1
      a_n=a_n/pi;
    endif
  endif
  a_n=a_n*cos(n*t);
endfunction
function b_n = fourier_bn(k1, k2, t, a, b, n, const)
  if const==1
    b_n=int(k1*sin(n*t),t,[a,0])+int(k2*sin(n*t),t,[0,b]);
  else
    b_n=int(k1*sin(n*pi*t),t,[a,0])+int(k2*sin(n*pi*t),t,[0,b]);
  endif
  if b_n~=0
    if const==1
      b_n=b_n/pi;
    endif
  endif
  b_n=b_n*sin(n*t);
endfunction
a_0=fourier_a0(2*(1+t),0,t,-1,1,0);
f1(1)=a_0;
for n=1:5
  disp(n);
  a_n=fourier_an(2*(1+t),0, t, -1, 1, n, 0);
  b_n=fourier_bn(2*(1+t),0, t, -1, 1, n, 0);
  if a_n==0
    f1(n+1)=b_n;
  endif
  if b_n==0
    f1(n+1)=a_n;
  endif
  if a_n~=0 && b_n~=0
    f1(n+1)=a_n+b_n;
  endif
end

a_0=fourier_a0(-4,4,t,-pi,pi,1);
f2(1)=a_0;
for n=1:3
  disp(n);
  a_n=fourier_an(-4,4,t,-pi,pi, n, 1);
  b_n=fourier_bn(-4,4,t,-pi,pi, n, 1);

  if a_n==0
    f2(n+1)=b_n;
  endif
  if b_n==0
    f2(n+1)=a_n;
  endif
  if a_n~=0 && b_n~=0
    f2(n+1)=a_n+b_n;
  endif
end
clc
disp(f1)
fprintf("\n\n")
disp(f2)
