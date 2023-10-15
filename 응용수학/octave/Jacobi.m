clear all
clc
format long
A=[4,1,1;1,3,-1;2,-1,5];
b=[17; 9; 1];
jacobi=[0; 0; 0];
N=diag(diag(A));
P=N-A;
n=1;
fprintf('n      x, y, z \n\n')
for k=1:20
    jacobi=(N)\(P*jacobi+b);
      fprintf("%0.0f \n",n);
      fprintf("decical \n");
      disp(jacobi);
      format rat
      fprintf("fraction \n");
      disp(jacobi);
      n=n+1;
      format long;
end
