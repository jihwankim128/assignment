clear all
clc
format long
A=[10, 2, 6; 1, 5,3; 4, 12, 20];
b=[18; 9; 36];
xi=[0; 0; 0];
N=tril(A);
P=N-A;
n=1;
fprintf('n      x1,x2,x3 \n\n')
for k=1:16
    xi=(N)\(P*xi+b);
    fprintf('%0.0f ',n);
    fprintf('소수점 표기 \n')
    disp(xi);
    n=n+1;
end
