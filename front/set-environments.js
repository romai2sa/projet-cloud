const fs = require('fs');
const path = require('path');

const apiUrl = process.env.BACKEND_URL || "http://çaAPasMarche:/";

const environments = {
  "": {
    production: true,
    apiUrl,
  },
  "local.": {
    production: false,
    apiUrl: 'http://localhost:8080',
  },
};

function writeEnvironmentFile(envName) {
  const targetPath = path.join(__dirname, `src/environments/environment.${envName}ts`);

  const envObject = environments[envName];
  const fileContent =
    `// ATTENTION ! Modifier ce fichier n'aura aucun impact car c'est un fichier généré par /set-environments.js\n`+
    `export const environment = {\n` +
    `  production: ${envObject.production},\n` +
    `  apiUrl: "${envObject.apiUrl}"\n` +
    `};\n`;

  fs.writeFileSync(targetPath, fileContent, { encoding: 'utf8' });
  console.log(`Environment file written: ${targetPath}`);
}

Object.keys(environments).forEach(writeEnvironmentFile);
