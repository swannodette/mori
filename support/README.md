# Testing script tag and AMD module loads

```javascript
npm install -g node-static
cd /path/to/mori/support
static -p 12345
```

Open a browser to `http://localhost:12345/` and follow the links to "script tag" and "AMD module".

Those pages don't feature full-blown tests of `mori`'s API. Rather, the point is to make sure that the library can be loaded properly with a `<script>` tag and as an AMD module.
