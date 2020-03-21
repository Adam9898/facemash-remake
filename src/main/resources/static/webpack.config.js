const path = require('path');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
module.exports = {
    mode: 'development',
    devtool: 'inline-source-map',
    entry: path.resolve(__dirname, 'ts/app.ts'),
    output: {
        filename: 'js/bundle.js',
        path: path.resolve(__dirname),
        libraryTarget: 'var',
        libraryExport: 'default',
        library: 'LogicConnector',
    },
    watch: true,
    plugins: [new MiniCssExtractPlugin({ filename: 'css/styles.css' })],
    module: {
        rules: [
            {
                test: /\.ts$/,
                use: ["ts-loader"]
            },
            {
                test: /\.css$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    'css-loader'
                ]
            },
            {
                test: /\.scss$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    'css-loader',
                    'sass-loader'
                ]
            },
            {
                test: /\.(woff2|woff|ttf)$/,
                use: [
                    {
                        loader: 'file-loader',
                        options: {
                            name: '[name].[ext]',
                            outputPath: '/fonts/final'
                        }
                    }
                ]
            }
        ]
    },
    resolve: {
        extensions: ['.ts', '.tsx', '.js', '.json', 'scss']
    }
}