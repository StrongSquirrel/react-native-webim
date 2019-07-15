require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
    s.name         = "RNWebim"
    s.version      = package['version']
    s.summary      = package['description']
    s.license      = package['license']
    s.homepage     = package['repository']['url']
    s.authors = package['author'] || "StrSqr"
    s.summary = package['description']
    s.source = { git: package['repository']['url'] }
    s.source_files = "ios/**/*.{h,m}"
    s.platform = :ios, "9.0"
    s.requires_arc = true
    s.preserve_paths = 'LICENSE', 'README.md', 'package.json', 'index.js'

    s.dependency "React"
    s.dependency "WebimClientLibrary"

end
