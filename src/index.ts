import { registerPlugin } from '@capacitor/core';

import type { VideoCompressionPlugin } from './definitions';

const VideoCompression = registerPlugin<VideoCompressionPlugin>(
  'VideoCompression',
  {},
);

export * from './definitions';
export { VideoCompression };
