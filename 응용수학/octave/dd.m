clear all
clc
format long
A=[-8, 1, 1; 1, -5, 1; 1, 1, -4];
b=[1; 16; 7];
xi=[0; 0; 0];
N=diag(diag(A));
P=N-A;
k=1;
n=1;
fprintf('n      x1,x2,x3 \n\n')
for k=1:10
    xi=(N)\(P*xi+b);
    fprintf("%0.0f \n",n);
    fprintf("decical \n");
    disp(xi);
    format rat                    % x1, x2, x3 이 동일하기에
    fprintf("fraction \n");      % 분수로도 표현해봄
    disp(xi);
    format long
    n=n+1;
end
