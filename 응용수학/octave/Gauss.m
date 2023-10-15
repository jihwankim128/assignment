clear all
clc
format long
A=[4,1,1;1,6,2;1,2,4];
b=[-1;0;1];
gauss=[0; 0; 0];
N=tril(A);
P=N-A;
n=1;
fprintf('n      x,y,z \n\n')
for k=1:10
    gauss=(N)\(P*gauss+b);
    fprintf('%0.0f ',n);
    format long
    fprintf("decical \n");
    disp(gauss);
    format rat
    fprintf("fraction \n");
    disp(gauss);
    n=n+1;
end
