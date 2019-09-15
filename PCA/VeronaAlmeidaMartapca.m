function [U, S] = VeronaAlmeidaMartapca(X)
%VERONAALMEIDAMARTAPCA Run principal component analysis on the dataset X
%   [U, S, X] = VeronaAlmeidaMartapca(X) computes eigenvectors of the covariance matrix of X
%   Returns the eigenvectors U, the eigenvalues (on diagonal) in S
%

% Useful values
[m, n] = size(X);

% You need to return the following variables correctly.
U = zeros(n);
S = zeros(n);

% ====================== YOUR CODE HERE ======================
% Instructions: You should first compute the covariance matrix. Then, you
%               should use the "svd" function to compute the eigenvectors
%               and eigenvalues of the covariance matrix. 
%
% Note: When computing the covariance matrix, remember to divide by m (the
%       number of examples).
%

% INCLUYE TU CÓDIGO AQUÍ
% Calculo la matriz de covarianzas
sigma = cov(X);

% Calculo valores y vectores propios
[U,S,V] = svd(sigma);

% =========================================================================

end
